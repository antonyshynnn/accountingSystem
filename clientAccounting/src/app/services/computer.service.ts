import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Computer} from "../computer";

@Injectable({
  providedIn: 'root'
})
export class ComputerService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getComputersById(computerId: number): Observable<Computer[]> {
    return this.http.get<Computer[]>(`${this.apiServerUrl}/computers/room/${computerId}`);
  }

  public getAllComputers(): Observable<Computer[]> {
    return this.http.get<Computer[]>(`${this.apiServerUrl}/computers/all`);
  }

  public addComputer(computer: Computer): Observable<Computer> {
    return this.http.post<Computer>(`${this.apiServerUrl}/computers/add`, computer);
  }

  public updateComputer(computer: Computer): Observable<Computer> {
    return this.http.put<Computer>(`${this.apiServerUrl}/computers/update`, computer);
  }

  public deleteComputer(computerId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/computers/delete/${computerId}`);
  }

}
