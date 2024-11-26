import { Component } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.css'
})

export class UserRegisterComponent {

  user = { name: '', email: '', password: '', contactNumber: '', address: '' };

  constructor(private userService: UserService) {}

  onRegister(userData: any) {
    this.userService.register(userData).subscribe(
      (response) => {
        alert('Registration successful!');
      },
      (error) => {
        alert('Registration failed: ' + error.error.message);
      }
    );
  }

}
