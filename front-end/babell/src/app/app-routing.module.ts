import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuardService} from './service/guard/auth-guard.service';
import {LoginComponent} from './component/login/login.component';

const routes: Routes = [
  {path: '', redirectTo: '/', pathMatch: 'full'},
  {path : '', canActivate: [AuthGuardService], loadChildren : () => import('./module/project/project.module').then(m => m.ProjectModule)},
  {path: 'login', component: LoginComponent},
  {path: '**', redirectTo: '/'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
