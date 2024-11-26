import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { UserRegisterComponent } from './user/user-register/user-register.component';
import { UserLoginComponent } from './user/user-login/user-login.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { HeaderComponent } from './home-page/header/header.component';

// const routes: Routes = [
//   { path: 'register', component: UserRegisterComponent },
//   { path: 'login', component: UserLoginComponent },
//   { path: 'profile', component: UserProfileComponent },
//  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Default route

 
//   ];

const routes: Routes = [
  
  {path : '', component: HeaderComponent},

  { path: 'users', loadChildren: () => import('./user/user.module').then(m => m.UserModule) },

  { path: 'register', component: UserRegisterComponent },

  {path: 'login', component: UserLoginComponent},

];


// const routes: Routes = [
//   {path: '/', component: HomeComponent ,children: [
//     { path: 'register', component: UserRegisterComponent },
//     { path: 'login', component: UserLoginComponent },
//     { path: 'profile', component: UserProfileComponent },
//     { path: '', redirectTo: '/login', pathMatch: 'full' }, // Default route
//   ]},

//   ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
 
}
