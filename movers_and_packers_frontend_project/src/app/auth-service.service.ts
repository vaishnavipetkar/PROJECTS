import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private baseUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  // login(credentials: any) {
  //   return this.http.post(`${this.baseUrl}/login`, credentials);
  // }

  // signup(user: any) {
  //   return this.http.post(`${this.baseUrl}/signup`, user);
  // }

  login(data: { email: string, password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, data);
  }

  signup(data: { name: string, email: string, password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, data);
  }
}
