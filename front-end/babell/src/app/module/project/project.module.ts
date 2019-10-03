import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectComponent } from './project.component';
import { ProjectContainerComponent } from './container/project-container/project-container.component';
import {ProjectRoutingModule} from './project-routing.module';
import { ProjectListComponent } from './component/project-list/project-list.component';
import { ProjectDetailComponent } from './component/project-detail/project-detail.component';
import { ProjectDetailContainerComponent } from './container/project-detail-container/project-detail-container.component';
import {SharedModule} from '../../shared/shared.module';
import { TodoListComponent } from './component/todo-list/todo-list.component';

@NgModule({
  declarations: [
    ProjectComponent,
    ProjectContainerComponent,
    ProjectListComponent,
    ProjectDetailComponent,
    ProjectDetailContainerComponent,
    TodoListComponent
  ],
  imports: [
    CommonModule,
    ProjectRoutingModule,
    SharedModule
  ]
})
export class ProjectModule { }
