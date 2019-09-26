import { Component, OnInit } from '@angular/core';
import {ProjectService} from '../../service/project.service';
import {ProjectInterface} from '../../model/project.interface';

@Component({
  selector: 'app-project-container',
  templateUrl: './project-container.component.html',
  styleUrls: ['./project-container.component.scss']
})
export class ProjectContainerComponent implements OnInit {

  projects: ProjectInterface[];

  constructor(private projectService: ProjectService) { }

  ngOnInit() {
    this.getProjects();
  }
  private getProjects() {
    this.projectService.getProjects().subscribe(
      response => this.projects = response
    );
  }
}
