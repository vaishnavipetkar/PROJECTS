import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})

export class UserProfileComponent implements OnInit {
  user: any;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    const userId = localStorage.getItem('userId');
    if (userId) {
      this.userService.getProfile(+userId).subscribe((response) => {
        this.user = response;
      });
    }
  }

  onDelete() {
    const userId = localStorage.getItem('userId');
    if (userId) {
      this.userService.deleteProfile(+userId).subscribe(() => {
        alert('Account deleted successfully!');
        localStorage.removeItem('userId');
        this.router.navigate(['/login']);
      });
    }
  }
}
