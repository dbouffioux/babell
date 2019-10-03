import { Injectable } from '@angular/core';
import {PersonBusiness} from '../../model/business/person.business';
import {JWToken} from '../../model/enum/jwtoken.enum';
import {LoginBusiness} from '../../model/business/login.business';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly JWTokenName: string;
  // tslint:disable-next-line:variable-name
  private _token: string;
  // tslint:disable-next-line:variable-name
  private _person: PersonBusiness;

  constructor(
    private http: HttpClient
  ) {
    this.person = null;
  }

  // getters / setters
  get person(): PersonBusiness {
    return this._person;
  }

  set person(value: PersonBusiness) {
    this._person = value;
  }
  get token(): string {
    return this._token;
  }

  set token(value: string) {
    this._token = value;
  }

  // methods
  public getToken(): string {
    return localStorage.getItem(JWToken.TOKEN_NAME);
  }

  /*
  // is not secure ..
  public refreshToken(): Observable<any> {
    return this.http.post<any>(`${environment.baseUrl}/refresh`, {
      refreshToken: localStorage.getItem(JWToken.TOKEN_REFRESH)
    }).pipe(tap((loginBusiness: LoginBusiness) => {
      this.setLoginStorage(loginBusiness);
    }));
  }
   */

  public isAuthenticated(): boolean {
    return !!this.getToken();
  }

  public removeLoginStorage(): void {
    localStorage.removeItem(JWToken.TOKEN_NAME);
    // localStorage.removeItem(JWToken.TOKEN_REFRESH);
    this.person = null;
  }

  public setLoginStorage(loginBusiness: LoginBusiness): void {
    this.token = loginBusiness.token;
    localStorage.setItem(JWToken.TOKEN_NAME, this.token);
    // localStorage.setItem(JWToken.TOKEN_REFRESH, loginBusiness.refreshToken)
  }

  public setUser(email: string) {
    // TODO : GET user by email, and register it completely (except pwd) in localstorage
    // + ADD encryption for localstorage (good practice?)
    this.person = new PersonBusiness(
      null,
      null,
      null,
      email,
      null
    );
  }
}
