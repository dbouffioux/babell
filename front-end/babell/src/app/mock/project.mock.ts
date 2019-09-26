import {HttpResponse, HttpRequest, HttpErrorResponse, HttpClient} from '@angular/common/http';
import {ProjectInterface} from '../project/model/project.interface';
import {ResponseInterface} from '../model/response.interface';

const ProjectList: ResponseInterface = {
  payload: [
    {
      idProjet: 1,
      name: 'Uno',
      startDate: new Date('2019-08-12'),
      endDate: new Date('2019-08-18')
    },
    {
      idProjet: 2,
      name: 'Due',
      startDate: new Date('2019-08-18'),
      endDate: new Date('2019-08-28')
    },
  ],
  status: 200,
  message: 'success'
};

const getProjects = (request: HttpRequest<any>): HttpResponse<any> => {
  return new HttpResponse({
    status: ProjectList.status,
    body: ProjectList
  });
};

export const listeners = [
  { url: '/', methods: 'GET', name: 'getProjects', response: getProjects },
];
