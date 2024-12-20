
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Station } from '../../models/station.model';
import { BookingRequest } from '../../models/booking_request.model';
import { BookingResponse } from '../../models/booking_response.model';

@Injectable({
  providedIn: 'root'
})
export class MetroService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getStations(): Observable<Station[]> {
    return this.http.get<Station[]>(`${this.baseUrl}/station/list`);
  }

  bookTicket(request: BookingRequest): Observable<BookingResponse> {
    return this.http.post<BookingResponse>(`${this.baseUrl}/ticket/book`, request);
  }

  validateEntry(ticketRef: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/station/enter?ticketReferenceNumber=${ticketRef}`);
  }

  validateExit(ticketRef: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/station/exit?ticketReferenceNumber=${ticketRef}`);
  }
}
