import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: '/stations', pathMatch: 'full' },
  { path: 'stations', loadComponent: () => import('./components/station-list/station-list.component').then(m => m.StationListComponent) },
  { path: 'book', loadComponent: () => import('./components/book-ticket/book-ticket.component').then(m => m.BookTicketComponent) },
  { path: 'validate', loadComponent: () => import('./components/ticket-validation/ticket-validation.component').then(m => m.TicketValidationComponent) }
];
