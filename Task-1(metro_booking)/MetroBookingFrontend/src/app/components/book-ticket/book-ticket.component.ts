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
  price: number | null = null;
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

  ngOnInit() {
    this.fetchStations();
  }

  fetchStations() {
    this.metroService.getStations().subscribe((stations) => {
      this.stations = stations;
    });
  }

  calculatePrice() {
    const fromStationNumber = this.bookingForm.get('fromStationNumber')?.value;
    const toStationNumber = this.bookingForm.get('toStationNumber')?.value;

    if (fromStationNumber && toStationNumber) {
      // Find the stations in the stations array based on station numbers
      const fromStation = this.stations.find(station => station.stationNumber === parseInt(fromStationNumber));
      const toStation = this.stations.find(station => station.stationNumber === parseInt(toStationNumber));

      console.log(fromStation?.price, toStation?.price)
      if (fromStation && toStation) {
        // Calculate the price using their station prices
        this.price = Math.abs(fromStation.price - toStation.price);
      } else {
        this.price = null; // Reset price if stations are not found
      }
    } else {
      this.price = null; // Reset price if stations are not selected
    }
  }


  onSubmit() {
    if (this.bookingForm.valid) {
      const bookingRequest = {
        ...this.bookingForm.value,
        price: this.price
      };

      this.metroService.bookTicket(bookingRequest).subscribe((response) => {
        this.bookingResponse = response;
      });
    }
  }
}
