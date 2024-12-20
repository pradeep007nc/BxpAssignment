import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MetroService } from '../../services/metro.service';
import { CommonModule } from '@angular/common';
import { Station } from '../../../models/station.model';
import { BookingResponse } from '../../../models/booking_response.model';

@Component({
  selector: 'app-book-ticket',
  templateUrl: './book-ticket.component.html',
  styleUrls: ['./book-ticket.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
})
export class BookTicketComponent implements OnInit {

  bookingForm: FormGroup;
  stations: Station[] = [];
  bookingResponse?: BookingResponse;

  constructor(
    private fb: FormBuilder,
    private metroService: MetroService
  ) {
    this.bookingForm = this.fb.group({
      passengerEmail: ['', [Validators.required, Validators.email]],
      fromStationNumber: ['', Validators.required],
      toStationNumber: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.metroService.getStations().subscribe(
      (data) => this.stations = data
    );
  }

  onSubmit(): void {
    if (this.bookingForm.valid) {
      this.metroService.bookTicket(this.bookingForm.value).subscribe(
        (response) => {
          this.bookingResponse = response;
        },
        (error) => {
          console.error('Booking failed:', error);
        }
      );
    }
  }

}
