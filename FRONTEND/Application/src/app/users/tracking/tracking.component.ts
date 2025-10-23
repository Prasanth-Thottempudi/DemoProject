import { AfterViewInit, Component } from '@angular/core';
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import OSM from 'ol/source/OSM';
import { fromLonLat } from 'ol/proj';
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import VectorSource from 'ol/source/Vector';
import VectorLayer from 'ol/layer/Vector';
import { Icon, Style, Stroke } from 'ol/style';
import LineString from 'ol/geom/LineString';

@Component({
  selector: 'app-tracking',
  templateUrl: './tracking.component.html',
  styleUrls: ['./tracking.component.css'],
})
export class TrackingComponent implements AfterViewInit {
  map!: Map;
  vectorSource!: VectorSource;
  routeLayer!: VectorLayer;

  // Fixed start point: Hyderabad HiTech City
  startCoords: [number, number] = [78.3773, 17.4426]; // [lng, lat]
  userCoords: [number, number] | null = null; // to be set from GPS

  etaMinutes: number | null = null;

  ngAfterViewInit() {
    this.initMap();
    this.getUserLocationAndRoute();
  }

  private initMap() {
    this.vectorSource = new VectorSource();

    this.routeLayer = new VectorLayer({
      source: this.vectorSource,
      style: new Style({
        stroke: new Stroke({
          color: '#007bff',
          width: 5,
          lineCap: 'round',
          lineJoin: 'round',
        }),
      }),
    });

    this.map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
        this.routeLayer,
      ],
      view: new View({
        center: fromLonLat(this.startCoords),
        zoom: 13,
      }),
    });

    // Add marker for start location (HiTech City) with bike icon
    const startFeature = new Feature({
      geometry: new Point(fromLonLat(this.startCoords)),
    });
    startFeature.setStyle(
      new Style({
        image: new Icon({
          src: 'https://twemoji.maxcdn.com/v/latest/72x72/1f6f5.png',
          scale: 1,
          anchor: [0.5, 1],
        }),
      })
    );
    this.vectorSource.addFeature(startFeature);
  }

  private getUserLocationAndRoute() {
    if (!navigator.geolocation) {
      alert('Geolocation is not supported by your browser');
      return;
    }

    navigator.geolocation.getCurrentPosition(
      (position) => {
        this.userCoords = [position.coords.longitude, position.coords.latitude];

        // Add user location marker (blue dot)
        const userFeature = new Feature({
          geometry: new Point(fromLonLat(this.userCoords)),
        });
        userFeature.setStyle(
          new Style({
            image: new Icon({
              src: 'https://maps.google.com/mapfiles/ms/icons/blue-dot.png',
              scale: 1,
              anchor: [0.5, 1],
            }),
          })
        );
        this.vectorSource.addFeature(userFeature);

        // Center map between start and user
        const centerLng = (this.startCoords[0] + this.userCoords[0]) / 2;
        const centerLat = (this.startCoords[1] + this.userCoords[1]) / 2;
        this.map.getView().setCenter(fromLonLat([centerLng, centerLat]));
        this.map.getView().setZoom(13);

        // Request route from OSRM API
        this.getRouteFromOSRM(this.startCoords, this.userCoords);
      },
      () => alert('Unable to retrieve your location')
    );
  }

  private getRouteFromOSRM(start: [number, number], end: [number, number]) {
    const url = `https://router.project-osrm.org/route/v1/bicycle/${start[0]},${start[1]};${end[0]},${end[1]}?overview=full&geometries=geojson`;

    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        if (data.code !== 'Ok') {
          alert('Error fetching route');
          return;
        }

        const route = data.routes[0];

        // Show ETA (duration in seconds -> minutes)
        this.etaMinutes = Math.round(route.duration / 60);

        // Convert geojson route coords to OpenLayers coordinates
        const routeCoords = route.geometry.coordinates.map(
          (coord: [number, number]) => fromLonLat(coord)
        );

        const routeFeature = new Feature({
          geometry: new LineString(routeCoords),
        });

        this.vectorSource.addFeature(routeFeature);
      })
      .catch(() => alert('Failed to fetch route'));
  }
}
