import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProjectBusiness} from '../../model/business/project.business';
import {ProjectService} from '../../service/project.service';
import {TodoBusiness} from '../../model/business/todo.business';
import {TodoService} from '../../service/todo.service';
import { Store, Select } from '@ngxs/store';
import { ProjectAction } from 'src/app/store/project-action';
import { ProjectState } from 'src/app/store/project-state';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-project-detail-container',
  templateUrl: './project-detail-container.component.html',
  styleUrls: ['./project-detail-container.component.scss']
})
export class ProjectDetailContainerComponent implements OnInit {

  public project: ProjectBusiness;
  public error: string;
  public message: string;
  public projectName: string;

  @Select(ProjectState.selectedProject)
  public project$: Observable<ProjectBusiness>;

  constructor(
    private activatedRoute: ActivatedRoute,
    private todoService: TodoService,
    private store: Store
  ) { }

  ngOnInit() {
    this.getProject();
  }

  private getProject() {
    this.activatedRoute.params.subscribe(
      params => {
        this.store.dispatch(new ProjectAction.LoadProject(params.name));
      }
    );
  }

  private createTodo(todo: TodoBusiness): void {
    this.project$.subscribe(project => {
      this.store.dispatch(
        new ProjectAction.AddTodo(todo, project.name)
      );
    });
  }
}
