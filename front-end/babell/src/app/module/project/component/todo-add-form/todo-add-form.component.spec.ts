import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoAddFormComponent } from './todo-add-form.component';

describe('TodoAddFormComponent', () => {
  let component: TodoAddFormComponent;
  let fixture: ComponentFixture<TodoAddFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TodoAddFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoAddFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
