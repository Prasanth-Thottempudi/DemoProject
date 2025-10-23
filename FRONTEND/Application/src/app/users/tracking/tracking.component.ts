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
import { Icon, Style } from 'ol/style';

@Component({
  selector: 'app-tracking',
  templateUrl: './tracking.component.html',
  styleUrls: ['./tracking.component.css'],
})
export class TrackingComponent implements AfterViewInit {
  map!: Map;

  ngAfterViewInit() {
    // Hyderabad HiTech City coordinates
    const hitechCityCoords = [78.3773, 17.4426]; // [lng, lat]

    // Create a marker feature at HiTech City
    const hitechCityFeature = new Feature({
      geometry: new Point(fromLonLat(hitechCityCoords)),
    });

    hitechCityFeature.setStyle(
      new Style({
        image: new Icon({
          src: 'https://twemoji.maxcdn.com/v/latest/72x72/1f6f5.png', // bike emoji png from Twitter Emojis
          scale: 1,
          anchor: [0.5, 1], // so bottom center of emoji points to the location
        }),
      })
    );

    // Create a vector source and layer to display the marker
    const vectorSource = new VectorSource({
      features: [hitechCityFeature],
    });

    const vectorLayer = new VectorLayer({
      source: vectorSource,
    });

    // Initialize the map with OSM layer and the marker layer
    this.map = new Map({
      target: 'map',
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
        vectorLayer,
      ],
      view: new View({
        center: fromLonLat(hitechCityCoords),
        zoom: 15, // zoom in a bit on the marker
      }),
    });
  }
}
