import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  private baseUrl = 'http://localhost:8080/services';

  constructor(private http: HttpClient) {}

  getServices() {
    return this.http.get<any[]>(this.baseUrl);
  }
}
