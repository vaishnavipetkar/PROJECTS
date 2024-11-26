import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) { }

  register(user: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }

  getProfile(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/getSingleUser/${userId}`);
  }

  updateProfile(userId: number, user: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${userId}`, user);
  }

  deleteProfile(userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${userId}`);
  }
  
}
