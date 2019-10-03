import {Component, Input, OnInit} from '@angular/core';
import {BusinessInterface} from '../../../../model/business.interface';
import {TodoBusiness} from '../../model/business/todo.business';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent implements OnInit {

  @Input() list: TodoBusiness[];
  constructor() { }

  ngOnInit() {
  }

}
