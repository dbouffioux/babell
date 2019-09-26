import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path: '', redirectTo: '/projects', pathMatch: 'full'},
  {path : 'projects', loadChildren : () => import('./project/project.module').then(m => m.ProjectModule)},
  {path: '**', redirectTo: '/projects'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
