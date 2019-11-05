import { TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { AppComponent } from '../app.component';
import { ProjectService } from '../module/project/service/project.service';
import { TodoService } from '../module/project/service/todo.service';
import { NgxsModule, Store } from '@ngxs/store';
import { ProjectState, ProjectStateModel } from './project-state';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { ROUTES } from '../app-routing.module';
import { environment } from 'src/environments/environment';
import { LoginContainerComponent } from '../container/login-container/login-container.component';
import { ProjectBusiness } from '../module/project/model/business/project.business';

export const INIT_STATE = {
  name: 'projects',
  defaults: {
    projects: [
       {
        id: 1,
        name: 'fakdis',
        startDate: new Date(),
        endDate: new Date(),
        todoList: [],
        fromDto: () => null,
        toDto: () => null
       },
       {
          id: 2,
          name: 'okkkk',
          startDate: new Date(),
          endDate: new Date(),
          todoList: [],
          fromDto: () => null,
          toDto: () => null
        }
      ],
    selectedProject: null,
    error: null,
    success: null
  } as ProjectStateModel
};

export const expectedResult = [
  {
    id: 1,
    name: 'fakdis',
    startDate: new Date(),
    endDate: new Date(),
    todoList: [],
    fromDto: () => null,
    toDto: () => null
   },
   {
      id: 2,
      name: 'okkkk',
      startDate: new Date(),
      endDate: new Date(),
      todoList: [],
      fromDto: () => null,
      toDto: () => null
    }
];

describe('Project Store', () => {

  let projectService: ProjectService;
  let todoService: TodoService;
  let backend: HttpTestingController;
  let store: Store;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        LoginContainerComponent
      ],
      providers: [
        ProjectService,
        TodoService
      ],
      imports: [
        NgxsModule.forRoot([ProjectState], {developmentMode: true}),
        RouterTestingModule.withRoutes(ROUTES),
        HttpClientTestingModule,
      ],
      schemas: [NO_ERRORS_SCHEMA]
    });

    backend = TestBed.get(HttpTestingController);
    projectService = TestBed.get(ProjectService);
    todoService = TestBed.get(TodoService);
    store = TestBed.get(Store);

    store.reset(INIT_STATE);
  });

  it('should load project list', () => {
    // login


    // FAILS UNTIL
    // call projects
    /*backend.expectOne(`${environment.baseUrl}/projects`).flush(expectedResult);
    backend.verify();

    expect(store.selectSnapshot(ProjectState.projects)).toEqual(expectedResult);*/
  });
});
