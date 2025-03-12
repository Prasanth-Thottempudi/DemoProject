import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent {
  registrationForm: FormGroup;

 

  ngOnInit(): void {}

 

  constructor(private fb: FormBuilder,private router:Router) {
    this.registrationForm = this.fb.group({
      firstName: ['firstname', [Validators.required]],
      lastName: ['lastname', [Validators.required]],
      email: ['demo@gmail.com', [Validators.required]],
      password: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required]],
    });
  }

  onSubmit(): void {
    console.log(this.registrationForm.value);
  }

  closeForm(){
    this.router.navigate(['/'])
  }
}
