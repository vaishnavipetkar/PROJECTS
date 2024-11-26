import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRegisterComponent } from './user-register/user-register.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UserLoginComponent } from './user-login/user-login.component';

@NgModule({
  declarations: [
    UserRegisterComponent,
    UserLoginComponent,
    UserProfileComponent
  ],
  imports: [
    CommonModule,
    FormsModule,  // Enables ngModel and template-driven forms
    HttpClientModule, // Enables HTTP client
  ],
  exports: [
    UserRegisterComponent, // Export components if needed in other modules
    UserLoginComponent,
    UserProfileComponent,
  ],
})
export class UserModule { }
