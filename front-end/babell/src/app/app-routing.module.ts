import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuardService} from './service/auth/guard/auth-guard.service';
import {LoginContainerComponent} from './container/login-container/login-container.component';

const routes: Routes = [
  {path: '', redirectTo: '/projects', pathMatch: 'full'},
  {path : 'projects',
    canActivate: [AuthGuardService],
    loadChildren : () => import('./module/project/project.module').then(m => m.ProjectModule)
  },
  {path: 'login', component: LoginContainerComponent},
  {path: '**', redirectTo: '/projects'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
