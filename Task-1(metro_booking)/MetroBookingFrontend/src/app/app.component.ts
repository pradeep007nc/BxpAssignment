import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <div class="navbar bg-blue-500 text-white p-4">
      <div class="container mx-auto flex justify-between items-center">
        <div class="text-lg font-semibold">Metro App</div>
        <div class="flex space-x-4">
          <a
            routerLink="/stations"
            routerLinkActive="active-link"
            class="nav-link"
            >Stations</a
          >
          <a routerLink="/book" routerLinkActive="active-link" class="nav-link"
            >Book Ticket</a
          >
          <a
            routerLink="/validate"
            routerLinkActive="active-link"
            class="nav-link"
            >Travel</a
          >
        </div>
      </div>
    </div>

    <div class="container mx-auto p-6">
      <router-outlet></router-outlet>
    </div>
  `,
})
export class AppComponent { }
