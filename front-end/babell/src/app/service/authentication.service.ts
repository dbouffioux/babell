import { Injectable } from '@angular/core';
import {PersonBusiness} from '../model/business/person.business';
import {JWToken} from '../model/enum/jwtoken.enum';
import {LoginBusiness} from '../model/business/login.business';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly JWTokenName: string;
  // tslint:disable-next-line:variable-name
  private _token: string;
  // tslint:disable-next-line:variable-name
  private _person: PersonBusiness;

  constructor() {
    this.JWTokenName = JWToken.TOKEN_NAME;
    this.token = '';
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
  public isLogged(): boolean {
    return localStorage.getItem('JWToken') !== null;
  }

  public removeLoginStorage(): void {
    localStorage.removeItem('JWToken');
    this.token = '';
  }

  public setLoginStorage(loginBusiness: LoginBusiness): void {
    this.token = loginBusiness.token;
    localStorage.setItem(this.JWTokenName, this.token);
  }

  setUser(email: string) {
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
