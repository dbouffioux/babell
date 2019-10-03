import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TodoBusiness} from '../model/business/todo.business';
import {Observable, throwError} from 'rxjs';
import {environment} from '../../../../environments/environment';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor(private http: HttpClient) { }

  public createTodo(todo: TodoBusiness, projectName: string): Observable<TodoBusiness> {
    return this.http
      .post<TodoBusiness>(`${environment.baseUrl}//todoproject/{{projectName}}`, todo,
        { withCredentials: true })
      .pipe(catchError((error: any) => throwError(error.json())));
  }
}
