import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectBusiness} from '../../model/business/project.business';
import {ProjectService} from '../../service/project.service';
import {TodoBusiness} from '../../model/business/todo.business';
import {TodoService} from '../../service/todo.service';

@Component({
  selector: 'app-project-detail-container',
  templateUrl: './project-detail-container.component.html',
  styleUrls: ['./project-detail-container.component.scss']
})
export class ProjectDetailContainerComponent implements OnInit {

  public project: ProjectBusiness;
  public error: string;
  private message: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private projectService: ProjectService,
    private todoService: TodoService
  ) { }

  ngOnInit() {
    this.getProject();
  }

  private getProject() {
    this.activatedRoute.params.subscribe(
      params => {
        this.projectService.getProjectByName(params.name).subscribe(
          response => {
            if (response.status !== 'SUCCESS') {
              this.error = response.message;
            } else {
              this.project = response.payload;
            }
          },
          error => this.error = error
        );
      }
    );
  }

  private createTodo(todo: TodoBusiness): void {
    this.todoService.createTodo(todo, this.project.name).subscribe(
      (response) => {
         if (response.status !== 'SUCCESS') {
            this.error = response.message;
         } else {
            this.message = response.message;
         }
         this.getProject();
      },
      (error) => this.error = error
    );
  }
}
