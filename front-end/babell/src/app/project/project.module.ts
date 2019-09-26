import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectComponent } from './project.component';
import { ProjectContainerComponent } from './container/project-container/project-container.component';

@NgModule({
  declarations: [
    ProjectComponent,
    ProjectContainerComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ProjectModule { }
