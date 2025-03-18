import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonServicesService } from '../services/common-services.service';

@Component({
  selector: 'app-admin-data-table',
  templateUrl: './admin-data-table.component.html',
  styleUrls: ['./admin-data-table.component.css'],
})
export class AdminDataTableComponent implements OnInit {
  approvalRequests: any[] = [];

  constructor(
    private route: Router,
    private fb: FormBuilder,
    private http: CommonServicesService
  ) {}
  ngOnInit(): void {
    this.http.adminServiceApprovalRequests().subscribe((response) => {
      console.log(response);
      this.approvalRequests = response;
    });
  }

  approveRequest() {
    console.log('approved request button invoked');
  }
}
