import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {ProjectBusiness} from '../model/business/project.business';
import {ProjectInterface} from '../model/project.interface';
import {ResponseInterface} from '../../../model/response.interface';
import {HTTPResponseAdapter} from '../../../utils/httpresponse-adapter';
import {environment} from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  public getProjects(): Observable<ProjectBusiness[]> {
    return this.http.get<ResponseInterface<ProjectInterface[]>>(
      `${environment.baseUrl}/projects`
    ).pipe(
      map(
        (response: ResponseInterface<ProjectInterface[]>) => {
        return HTTPResponseAdapter.adapt(response, ProjectBusiness);
      }
    ));
  }

  public getProjectByName(name: string): Observable<ProjectBusiness> {
    return this.http.get<ResponseInterface<ProjectInterface>>(
      `${environment.baseUrl}/todoproject/${name}`
    ).pipe(
      map(
        (response) => {
          console.log(response);
          return HTTPResponseAdapter.adapt(response, ProjectBusiness);
        }
      )
    );
  }
}
