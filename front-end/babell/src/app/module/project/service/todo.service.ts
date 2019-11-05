import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TodoBusiness} from '../model/business/todo.business';
import {Observable, throwError} from 'rxjs';
import {environment} from '../../../../environments/environment';
import {catchError} from 'rxjs/operators';
import {ResponseInterface} from '../../../model/response.interface';
import {TodoInterface} from '../model/todo.interface';

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  constructor(private http: HttpClient) { }

  public createTodo(todo: TodoBusiness, projectName: string): Observable<ResponseInterface<TodoInterface>> {
    return this.http
      .post<ResponseInterface<TodoInterface>>(`${environment.baseUrl}/todoproject/${projectName}`, todo.toDto(),
        { withCredentials: true })
      .pipe(catchError((error: any) => throwError(error.json())));
  }
}
