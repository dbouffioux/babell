import { Component, Input, OnInit } from '@angular/core';
import { ProjectBusiness } from '../../model/business/project.business';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.scss']
})
export class ProjectListComponent implements OnInit {

  @Input() public projects$: Observable<ProjectBusiness[]>;
  constructor() { }

  ngOnInit() {
  }

}
