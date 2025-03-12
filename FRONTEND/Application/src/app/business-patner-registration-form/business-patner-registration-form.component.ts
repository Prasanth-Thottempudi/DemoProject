import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-business-patner-registration-form',
  templateUrl: './business-patner-registration-form.component.html',
  styleUrls: ['./business-patner-registration-form.component.css'],
})
export class BusinessPatnerRegistrationFormComponent implements OnInit {
  step1FormGroup: FormGroup;
  step2FormGroup: FormGroup;

  constructor(private _formBuilder: FormBuilder, private route: Router) {}

  ngOnInit() {
    this.step1FormGroup = this._formBuilder.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      gender: ['', Validators.required],
      nationality: ['', Validators.required],
    });

    this.step2FormGroup = this._formBuilder.group({
      serviceName: ['', Validators.required],
      businessName: ['', Validators.required],
      businessEmail: ['', [Validators.required, Validators.email]],
      businessMobileNumber: ['', Validators.required],
      businessRegion: ['', Validators.required],
      language: ['', Validators.required],
      emergencyContact: ['', Validators.required],
      businessDescription: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.step1FormGroup.valid && this.step2FormGroup.valid) {
      const formData = {
        ...this.step1FormGroup.value,
        ...this.step2FormGroup.value,
      };
      console.log('Form Data:', formData);
    }
  }

  closeForm() {
    console.log('cancel button is triggered');
    this.route.navigate(['/']);
  }
}
