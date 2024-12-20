import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
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
    return this.http.get<Station[]>(`${this.baseUrl}/station/list`).pipe(
      catchError(error => {
        this.handleError(error);
        return throwError(error);
      })
    );
  }

  bookTicket(request: BookingRequest): Observable<BookingResponse> {
    return this.http.post<BookingResponse>(`${this.baseUrl}/ticket/book`, request).pipe(
      catchError(error => {
        this.handleError(error);
        return throwError(error);
      })
    );
  }

  validateEntry(ticketRef: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/station/enter?ticketReferenceNumber=${ticketRef}`).pipe(
      catchError(error => {
        this.handleError(error);
        return throwError(error);
      })
    );
  }

  validateExit(ticketRef: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/station/exit?ticketReferenceNumber=${ticketRef}`).pipe(
      catchError(error => {
        this.handleError(error);
        return throwError(error);
      })
    );
  }

  private handleError(error: any): void {
    let errorMessage = 'An error occurred!';

    // Check if error has a specific structure (e.g., custom error message from the server)
    if (error.error && error.error.message) {
      // Use the message from the server if it exists
      errorMessage = error.error.message;
    } else if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error with status code and message
      errorMessage = `Server returned code: ${error.status}, error message: ${error.message}`;
    }

    alert(errorMessage);  // Display error message in an alert
  }

}
