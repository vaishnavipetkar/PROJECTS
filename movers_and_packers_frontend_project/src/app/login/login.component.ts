import { Component } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginData = { email: '', password: '' };

  constructor(private authService: AuthServiceService) {}

  onLogin() {
    this.authService.login(this.loginData).subscribe(response => {
      console.log('Login successful', response);
      // Handle successful login, navigate to dashboard or services
    }, error => {
      console.error('Login failed', error);
    });
  }
}
