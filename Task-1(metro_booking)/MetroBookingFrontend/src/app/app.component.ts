import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <nav class="bg-gray-800 p-4">
      <div class="container mx-auto flex gap-4">
        <a routerLink="/stations" class="text-white">Stations</a>
        <a routerLink="/book" class="text-white">Book Ticket</a>
        <a routerLink="/validate" class="text-white">Validate Ticket</a>
      </div>
    </nav>

    <router-outlet></router-outlet>
  `
})
export class AppComponent {}
