import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {map} from 'rxjs/operators';
import {environment} from 'src/environments/environment';
import {PersonBusiness} from '../model/business/person.business';
import {ResponseInterface} from '../model/response.interface';
import {HTTPResponseAdapter} from '../utils/httpresponse-adapter';
import {PersonInterface} from '../model/person.interface';
import {AuthenticationService} from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public payload: string;
  private params: HttpParams;

  constructor(
    private http: HttpClient,
    private auth: AuthenticationService
  ) {}

  public getConnection(login: string, password: string): Observable<any[] | any> {
    this.params = new HttpParams().set(
      'btoa',
      btoa(`{
        "username": ${login},
        "password": ${password}
        }`
      )
    );
    this.params.set('observe', 'response');
    return this.http.post<ResponseInterface<PersonInterface>>(`${environment.baseUrl}/login`, this.params,
      { withCredentials: true, headers: {'Content-Type' : 'application/json;charset=UTF-8'}}).pipe(
      map(
        (response: ResponseInterface<PersonInterface>) => {
          return HTTPResponseAdapter.adapt(response, PersonBusiness);
        }
      )
    );
  }

  public closeConnection(): void {
    this.http.get(`${environment.baseUrl}/logout`, { withCredentials: true }).subscribe(
      (success: any) => this.auth.removeLoginStorage(),
      (error: any) => throwError(error.json())
    );
  }
}
