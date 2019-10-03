import {Component, Input, OnInit} from '@angular/core';
import {ProjectBusiness} from '../../model/business/project.business';

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.scss']
})
export class ProjectDetailComponent implements OnInit {

  @Input() public project: ProjectBusiness;

  constructor() {}

  ngOnInit() {

  }
}
