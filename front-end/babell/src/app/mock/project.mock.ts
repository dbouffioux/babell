import {HttpResponse, HttpRequest, HttpErrorResponse, HttpClient} from '@angular/common/http';
import {ProjectInterface} from '../module/project/model/project.interface';
import {ResponseInterface} from '../model/response.interface';
import {TodoInterface} from '../module/project/model/todo.interface';

const ProjectList: ResponseInterface<ProjectInterface[]> = {
  payload: [
    {
      idProjet: 1,
      name: 'Uno',
      projectStart: new Date('2019-08-12'),
      projectEnd: new Date('2019-08-18'),
      todoDtoList: []
    },
    {
      idProjet: 2,
      name: 'Due',
      projectStart: new Date('2019-08-18'),
      projectEnd: new Date('2019-08-28'),
      todoDtoList: []
    },
  ],
  status: 200,
  message: 'success'
};

const Project: ResponseInterface<ProjectInterface> = {
  payload: {
    idProjet: 1,
    name: 'Uno',
    projectStart: new Date('2019-08-12'),
    projectEnd: new Date('2019-08-18'),
    todoDtoList: [
      {
        id: 1,
        name: 'blabla',
        description: 'tralalalala',
        estimation: 2,
        inProgress: true,
        done: false
      },
      {
        id: 2,
        name: 'deuxieme todo',
        description: 'voici ce qu\'il faut faire .. ',
        estimation: 2,
        inProgress: false,
        done: false
      }
    ]
  },
  status: 200,
  message: 'success'
};

const getProjects = (request: HttpRequest<any>): HttpResponse<any> => {
  return new HttpResponse({
    status: ProjectList.status,
    body: ProjectList
  });
};

const getProject = (request: HttpRequest<any>): HttpResponse<any> => {
  return new HttpResponse({
    status: Project.status,
    body: Project
  });
};

export const listeners = [
  { url: '/Uno', methods: 'GET', name: 'getProject', response: getProject },
  { url: '/', methods: 'GET', name: 'getProjects', response: getProjects },
];
