import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectComponent } from './project.component';
import { ProjectContainerComponent } from './container/project-container/project-container.component';
import {ProjectRoutingModule} from './project-routing.module';
import { ProjectListComponent } from './component/project-list/project-list.component';
import { ProjectDetailComponent } from './component/project-detail/project-detail.component';
import { ProjectDetailContainerComponent } from './container/project-detail-container/project-detail-container.component';
import {SharedModule} from '../../shared/shared.module';
import { TodoAddFormComponent } from './component/todo-add-form/todo-add-form.component';
import {ReactiveFormsModule} from '@angular/forms';
import {TodoListComponent} from './component/todo-list/todo-list.component';
import {AppModule} from '../../app.module';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [
    ProjectComponent,
    ProjectContainerComponent,
    ProjectListComponent,
    ProjectDetailComponent,
    ProjectDetailContainerComponent,
    TodoAddFormComponent,
    TodoListComponent
  ],
  imports: [
    CommonModule,
    ProjectRoutingModule,
    SharedModule,
    ReactiveFormsModule,
  ]
})
export class ProjectModule { }
