import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';
import {environment} from 'src/environments/environment';
import {ResponseInterface} from '../../model/response.interface';
import {HTTPResponseAdapter} from '../../utils/httpresponse-adapter';
import {AuthenticationService} from './authentication.service';
import {LoginInterface} from '../../model/login.interface';
import {LoginBusiness} from '../../model/business/login.business';

/**
 * Login service, used to get connection from server
 * or to close connection. A JWT token is generated on
 * server, and sent to user once successful log in.
 * JWT is used to reach server from any HTTP query type (POST, GET, ..)
 */
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

  public getConnection(email: string, password: string): Observable<LoginInterface> {
    this.params = new HttpParams().set(
      'btoa',
      btoa(`${email}:${password}`)
    );
    return this.http.post<ResponseInterface<LoginInterface>>(`${environment.baseUrl}/login`,
      this.params,
      { headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).pipe(
        map(
          (response: ResponseInterface<LoginInterface>) => {
            return HTTPResponseAdapter.adapt(response, LoginBusiness);
          }
        )
    );
  }

  public closeConnection(): void {
    this.http.get(`${environment.baseUrl}/logout`,
      { withCredentials: true }
      ).pipe(
        tap(
          () => this.auth.removeLoginStorage()
        )
    ).subscribe(
      (success: any) => '', // TODO : return a successfully disconnect message
      (error: any) => throwError(error.json())
    );
  }
}
