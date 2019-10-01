import { Injectable } from '@angular/core';
import {PersonBusiness} from '../model/business/person.business';
import {JWToken} from '../model/enum/jwtoken.enum';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly JWTokenName: string;
  // tslint:disable-next-line:variable-name
  private _token: string;

  constructor() {
    this.JWTokenName = JWToken.TOKEN_NAME;
    this.token = '';
  }

  get token(): string {
    return this._token;
  }

  set token(value: string) {
    this._token = value;
  }

  public isLogged(): boolean {
    return localStorage.getItem('JWToken') !== null;
  }

  public removeLoginStorage(): void {
    localStorage.removeItem('JWToken');
    this.token = '';
  }

  public setLoginStorage(token: string): void {
    this.token = token;
    localStorage.setItem(this.JWTokenName, this.token);
  }
}
