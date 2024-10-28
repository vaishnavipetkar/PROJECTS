import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-service',
  templateUrl: './service.component.html',
  styleUrl: './service.component.css'
})
export class ServicesComponent implements OnInit {
  services: any[] = [];

  constructor(private serviceService: ServiceService) {}

  ngOnInit() {
    this.serviceService.getServices().subscribe(data => {
      this.services = data;
    });
  }
}
