import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../../service/project.service';
import {ProjectBusiness} from '../../model/business/project.business';
import { Store, Select } from '@ngxs/store';
import { ProjectState } from 'src/app/store/project-state';
import { Observable } from 'rxjs';
import { ProjectAction } from 'src/app/store/project-action';

@Component({
  selector: 'app-project-container',
  templateUrl: './project-container.component.html',
  styleUrls: ['./project-container.component.scss']
})
export class ProjectContainerComponent implements OnInit {

  @Select(ProjectState.projects)
  public projects$: Observable<ProjectBusiness[]>;

  // projects: ProjectBusiness[];
  // private error: string;

  constructor(
    // private projectService: ProjectService,
    private store: Store
  ) {}

  ngOnInit() {
    this.getProjects();
  }

  private getProjects() {
    // with store
    this.store.dispatch(new ProjectAction.LoadList());

    // before with direct service call
    /*this.projectService.getProjects().subscribe(
      response => {
        if (response.status !== 'SUCCESS') {
          this.error = response.message;
        } else {
          this.projects = response.payload;
        }
      },
      error => this.error = error
    );*/
  }
}
