import { Component, OnInit } from '@angular/core';
import { MetroService } from '../../services/metro.service';
import { FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ticket-validation',
  templateUrl: './ticket-validation.component.html',
  styleUrls: ['./ticket-validation.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
})
export class TicketValidationComponent implements OnInit {

  ticketReference = new FormControl('', Validators.required);
  validationMessage = '';
  isError = false;

  constructor(private metroService: MetroService) {}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  validateEntry(): void {
    const ticketRefValue = this.ticketReference.value;

    if (ticketRefValue && this.ticketReference.valid) {
      this.metroService.validateEntry(ticketRefValue).subscribe(
        (response) => {
          this.validationMessage = response.message;
          this.isError = false;
        },
        (error) => {
          this.validationMessage = error.error.message;
          this.isError = true;
        }
      );
    }
  }

  validateExit(): void {
    const ticketRefValue = this.ticketReference.value;

    if (ticketRefValue && this.ticketReference.valid) {
      this.metroService.validateExit(ticketRefValue).subscribe(
        (response) => {
          this.validationMessage = response.message;
          this.isError = false;
        },
        (error) => {
          this.validationMessage = error.error.message;
          this.isError = true;
        }
      );
    }
  }

}
