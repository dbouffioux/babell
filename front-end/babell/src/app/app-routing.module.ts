import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuardService} from './service/guard/auth-guard.service';
import {LoginContainerComponent} from './container/login-container/login-container.component';

const routes: Routes = [
  {path: '', redirectTo: '/', pathMatch: 'full'},
  {path : '', canActivate: [AuthGuardService], loadChildren : () => import('./module/project/project.module').then(m => m.ProjectModule)},
  {path: 'login', component: LoginContainerComponent},
  {path: '**', redirectTo: '/'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
