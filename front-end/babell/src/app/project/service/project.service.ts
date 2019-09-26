import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {environment} from '../../../environments/environment';
import {catchError, map} from 'rxjs/operators';
import {ProjectInterface} from '../model/project.interface';
import {ProjectBusiness} from '../model/business/project.business';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  public getProjects(): Observable<ProjectBusiness[]> {
    return this.http.get<ProjectInterface[]>(`${environment.baseUrl}/`, /*{ withCredentials: true }*/)
      .pipe(
        map((projects: ProjectInterface[]) => {
          return projects.map((project: ProjectInterface) => {
            return ProjectBusiness.fromDTO(project);
          });
        }),
        catchError((error: any) => throwError(error.json()))
      );
  }
}
