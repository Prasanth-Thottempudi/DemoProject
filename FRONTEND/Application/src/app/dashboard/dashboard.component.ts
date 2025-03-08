import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  services = [
    {
      "serviceId": "serv001",
      "serviceName": "sample",
      "serviceDescription": "This is a sample service description",
      "serviceImageUrl": "http://192.168.0.134:9000/nexgenhub/sampleODL.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20250308%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250308T155127Z&X-Amz-Expires=7200&X-Amz-SignedHeaders=host&X-Amz-Signature=2a7899f329e409abcf21adec66090bc1eca7abdef7a482fcbf97fdd9a002d56f",
      "serviceImageName": "ODL.jpeg"
    },
    {
      "serviceId": "serv002",
      "serviceName": "sample1",
      "serviceDescription": "This is a sample service description",
      "serviceImageUrl": "http://192.168.0.134:9000/nexgenhub/sampleODL.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20250308%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250308T155127Z&X-Amz-Expires=7200&X-Amz-SignedHeaders=host&X-Amz-Signature=2a7899f329e409abcf21adec66090bc1eca7abdef7a482fcbf97fdd9a002d56f",
      "serviceImageName": "ODL.jpeg"
    },
    {
      "serviceId": "serv003",
      "serviceName": "sample2",
      "serviceDescription": "This is another sample service description",
      "serviceImageUrl": "http://192.168.0.134:9000/nexgenhub/sampleODL.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20250308%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250308T155127Z&X-Amz-Expires=7200&X-Amz-SignedHeaders=host&X-Amz-Signature=2a7899f329e409abcf21adec66090bc1eca7abdef7a482fcbf97fdd9a002d56f",
      "serviceImageName": "ODL.jpeg"
    },
    {
      "serviceId": "serv004",
      "serviceName": "sample3",
      "serviceDescription": "This is yet another sample service description",
      "serviceImageUrl": "http://192.168.0.134:9000/nexgenhub/sampleODL.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20250308%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250308T155127Z&X-Amz-Expires=7200&X-Amz-SignedHeaders=host&X-Amz-Signature=2a7899f329e409abcf21adec66090bc1eca7abdef7a482fcbf97fdd9a002d56f",
      "serviceImageName": "ODL.jpeg"
    },
    {
      "serviceId": "serv005",
      "serviceName": "sample4",
      "serviceDescription": "This is a new sample service description",
      "serviceImageUrl": "http://192.168.0.134:9000/nexgenhub/sampleODL.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20250308%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250308T155127Z&X-Amz-Expires=7200&X-Amz-SignedHeaders=host&X-Amz-Signature=2a7899f329e409abcf21adec66090bc1eca7abdef7a482fcbf97fdd9a002d56f",
      "serviceImageName": "ODL.jpeg"
    },
    {
      "serviceId": "serv006",
      "serviceName": "sample5",
      "serviceDescription": "This is an additional sample service description",
      "serviceImageUrl": "http://192.168.0.134:9000/nexgenhub/sampleODL.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20250308%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250308T155127Z&X-Amz-Expires=7200&X-Amz-SignedHeaders=host&X-Amz-Signature=2a7899f329e409abcf21adec66090bc1eca7abdef7a482fcbf97fdd9a002d56f",
      "serviceImageName": "ODL.jpeg"
    },
    {
      "serviceId": "serv007",
      "serviceName": "sample6",
      "serviceDescription": "This is a final sample service description",
      "serviceImageUrl": "http://192.168.0.134:9000/nexgenhub/sampleODL.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=admin%2F20250308%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20250308T155127Z&X-Amz-Expires=7200&X-Amz-SignedHeaders=host&X-Amz-Signature=2a7899f329e409abcf21adec66090bc1eca7abdef7a482fcbf97fdd9a002d56f",
      "serviceImageName": "ODL.jpeg"
    }
  ]
  
  
}
