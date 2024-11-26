import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { UserRegisterComponent } from './user/user-register/user-register.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { FormsModule } from '@angular/forms';
import { UserService } from './user/user.service';
import { UserModule } from './user/user.module';
import { HomePageModule } from './home-page/home-page.module';
import { RouterModule } from '@angular/router';

@NgModule({

  declarations: [
    AppComponent,
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,  // Enables ngModel and template-driven forms
    HttpClientModule, // Enables HTTP client
    AppRoutingModule, // Includes routing configurations
    UserModule,
    HomePageModule,
    RouterModule
  ],

  exports: [
    UserModule,
    HomePageModule
  ],

  providers: [], // Provide UserService for dependency injection

  bootstrap: [AppComponent] // Bootstraps the root component
})
export class AppModule { }

/*
Explanation of Each Section
1. Imports
BrowserModule: Essential for any Angular web application.
FormsModule: Required for template-driven forms using [(ngModel)].
HttpClientModule: Enables communication with the backend via HTTP.
AppRoutingModule: Contains the routing configuration for navigation.
2. Declarations
Lists all components created for the application:

UserRegisterComponent
UserLoginComponent
UserProfileComponent
These components must be declared in the module to be used within the application.

3. Providers
UserService: Added to the providers array to enable dependency injection for service classes.
4. Bootstrap
AppComponent: The root component, which is the entry point for the Angular application.


Key Use Case of exports: []
1. Reusability Across Modules
When you want a component, directive, pipe, or even another module to be reused in a different module, you include it in the exports array. This makes it accessible to any module that imports the current module.

Example Use Case
Scenario
Imagine you have a SharedModule that includes commonly used components like a header, footer, or a custom directive.

shared.module.ts:

typescript
Copy code
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    HeaderComponent, 
    FooterComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    HeaderComponent, // Making HeaderComponent reusable
    FooterComponent  // Making FooterComponent reusable
  ]
})
export class SharedModule {}
Here, the HeaderComponent and FooterComponent are added to the exports array, making them available to any module that imports SharedModule.
*/
