
import {AuthGuardService} from './service/auth/guard/auth-guard.service';
import {LoginContainerComponent} from './container/login-container/login-container.component';
import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';

const routes: Routes = [
  {path: '', redirectTo: '/projects', pathMatch: 'full'},
  {path : 'projects',
    canActivate: [AuthGuardService],
    loadChildren : './module/project/project.module#ProjectModule'
  },
  {path: 'login', component: LoginContainerComponent},
  {path: '**', redirectTo: '/projects'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
