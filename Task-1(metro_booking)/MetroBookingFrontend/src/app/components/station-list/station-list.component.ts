import { Component, OnInit } from '@angular/core';
import { MetroService } from '../../services/metro.service';
import { Station } from '../../../models/station.model';

@Component({
  selector: 'app-station-list',
  templateUrl: './station-list.component.html',
  styleUrls: ['./station-list.component.css']
})
export class StationListComponent implements OnInit {

  stations: Station[] = [];

  constructor(private metroService: MetroService) {}

  ngOnInit(): void {
    this.metroService.getStations().subscribe(
      (data) => this.stations = data
    );
  }

}
