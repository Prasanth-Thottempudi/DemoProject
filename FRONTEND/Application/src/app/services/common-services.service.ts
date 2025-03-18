import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CommonServicesService {
  constructor(private http: HttpClient) {}

  userLogin(payload:any):Observable<any>{
    return this.http.post('http://localhost:9091/user/auth/login', payload);
  }
}
