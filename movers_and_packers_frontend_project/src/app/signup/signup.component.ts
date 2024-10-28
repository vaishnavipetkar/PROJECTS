import { Component } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})

export class SignupComponent {

  signupData = { name: '', email: '', password: '' };

  constructor(private authService: AuthServiceService) {}

  onSignup() {
    this.authService.signup(this.signupData).subscribe(response => {
      console.log('Signup successful!', response);
    }, error => {
      console.error('Signup failed', error);
    });
  }

}
