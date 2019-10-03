import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProjectBusiness} from '../../model/business/project.business';

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

  submitForm(formValues: any): void {
    this.todoFormEmitter.emit(this.todoForm.value);
  }
}
