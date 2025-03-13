import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CommonServicesService } from '../services/common-services.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  closeForm() {}

  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private serv: CommonServicesService) {
    this.loginForm = this.fb.group({
      username: [''],
      password: [''],
    });
  }

  onSubmit() {
    console.log(this.loginForm.value);
    this.serv.userLogin(this.loginForm.value);
    console.log('called');

    
      const newData = { name: 'Sample Data' };
      this.serv.userLogin(this.loginForm.value).subscribe(
        (response) => {
          console.log('Data added successfully:', response);
        },
        (error) => {
          console.error('Error adding data:', error);
        }
      );
    }
  
}
