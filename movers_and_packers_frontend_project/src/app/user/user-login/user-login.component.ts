import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})
export class UserLoginComponent {

  credentials = { email: '', password: '' };

  constructor(private userService: UserService, private router: Router) {}

  onLogin(credentials: any) {
    this.userService.login(credentials).subscribe(
      (response: { userId: string; }) => {
        localStorage.setItem('userId', response.userId); // Store user ID for profile
        this.router.navigate(['/profile']);
      },
      (error: { error: { message: string; }; }) => {
        alert('Login failed: ' + error.error.message);
      }
    );
  }

}
