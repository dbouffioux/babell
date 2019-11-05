import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TodoBusiness } from '../../model/business/todo.business';

@Component({
  selector: 'app-todo-add-form',
  templateUrl: './todo-add-form.component.html',
  styleUrls: ['./todo-add-form.component.scss']
})
export class TodoAddFormComponent implements OnInit {
  private todoForm: FormGroup;
  @Output() private todoFormEmitter = new EventEmitter();

  constructor(private fb: FormBuilder) {
    this.todoForm = this.fb.group({
      name: this.fb.control('', [Validators.required]),
      description: this.fb.control('', [Validators.required])
    });
  }

  ngOnInit() {
  }

  submitForm(): void {
    const todo: TodoBusiness = this.createTodoBusiness();
    this.todoFormEmitter.emit(todo);
    this.todoForm.reset();
  }

  createTodoBusiness(): TodoBusiness {
    return new TodoBusiness(
      null,
      this.todoForm.value.name,
      this.todoForm.value.description,
      null,
      null,
      null,
      null
    );
  }
}
