import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectComponent } from './project.component';
import { ProjectContainerComponent } from './container/project-container/project-container.component';
import {ProjectRoutingModule} from './project-routing.module';
import { ProjectListComponent } from './component/project-list/project-list.component';

@NgModule({
  declarations: [
    ProjectComponent,
    ProjectContainerComponent,
    ProjectListComponent
  ],
  imports: [
    CommonModule,
    ProjectRoutingModule
  ]
})
export class ProjectModule { }
