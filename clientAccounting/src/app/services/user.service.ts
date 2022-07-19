import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import {UserEntity} from "../user";
import {environment} from "../../environments/environment";

const API_URL = 'http://localhost:8080/users/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiServerUrl = API_URL;

  constructor(private http: HttpClient) { }
  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  public updateUserEntity(user: UserEntity): Observable<UserEntity> {
    return this.http.put<UserEntity>(`${this.apiServerUrl}/users/update/`, user);
  }

  public getUserEntityByUsername(username: string): Observable<UserEntity> {
    return this.http.get<UserEntity>(`${this.apiServerUrl}/users/find/${username}`);

  }

  public getUserMoney(username: string): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/users/getUserMoney/${username}`);

  }
}
