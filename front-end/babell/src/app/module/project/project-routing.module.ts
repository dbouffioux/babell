
import {ProjectContainerComponent} from './container/project-container/project-container.component';
import {ProjectDetailContainerComponent} from './container/project-detail-container/project-detail-container.component';
import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';

const routes: Routes = [
  {path: '', pathMatch: 'full', component: ProjectContainerComponent},
  {path: ':name', pathMatch: 'full', component: ProjectDetailContainerComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectRoutingModule { }
