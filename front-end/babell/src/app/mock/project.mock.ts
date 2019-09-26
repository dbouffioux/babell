import {HttpResponse, HttpRequest, HttpErrorResponse, HttpClient} from '@angular/common/http';
import {ProjectInterface} from '../project/model/project.interface';

const ProjectList: ProjectInterface[] = [
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
];

const getProjects = (request: HttpRequest<any>): HttpResponse<any> => {
  return new HttpResponse({
    status: 200,
    body: ProjectList
  });
};

export const listeners = [
  { url: '/', methods: 'GET', name: 'getProjects', response: getProjects },
];
