import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../../service/project.service';
import {ProjectBusiness} from '../../model/business/project.business';

@Component({
  selector: 'app-project-container',
  templateUrl: './project-container.component.html',
  styleUrls: ['./project-container.component.scss']
})
export class ProjectContainerComponent implements OnInit {

  projects: ProjectBusiness[];
  private error: string;

  constructor(private projectService: ProjectService) { }

  ngOnInit() {
    this.getProjects();
  }

  private getProjects() {
    this.projectService.getProjects().subscribe(
      response => {
        if (response.status !== 'SUCCESS') {
          this.error = response.message;
        } else {
          this.projects = response.payload;
        }
      },
      error => this.error = error
    );
  }
}
