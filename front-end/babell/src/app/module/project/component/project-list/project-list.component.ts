import {Component, Input, OnInit} from '@angular/core';
import {ProjectBusiness} from '../../model/business/project.business';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.scss']
})
export class ProjectListComponent implements OnInit {

  @Input() private projects: ProjectBusiness[];
  constructor() { }

  ngOnInit() {
  }

}
