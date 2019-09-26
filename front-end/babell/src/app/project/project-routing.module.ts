import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProjectContainerComponent} from './container/project-container/project-container.component';

const routes: Routes = [
  {path : '/', component: ProjectContainerComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
