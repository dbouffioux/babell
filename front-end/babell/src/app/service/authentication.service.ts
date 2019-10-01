import { Injectable } from '@angular/core';
import {PersonBusiness} from '../model/business/person.business';
import {JWToken} from '../model/enum/jwtoken.enum';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly JWTokenName: string;
  // tslint:disable-next-line:variable-name
  private _person: PersonBusiness;

  constructor() {
    this.JWTokenName = JWToken.TOKEN_NAME;
  }

  get person(): PersonBusiness {
    return this._person;
  }

  set person(value: PersonBusiness) {
    this._person = value;
  }

  /**
   * This check is not enough to guarantee resources access security,
   * but token will be used for every http queries to server and without it
   * nothing will work.
   */
  public isLogged(): boolean {
    return localStorage.getItem('JWToken') !== null
      && localStorage.getItem('Person') !== null;
  }

  public removeLoginStorage(): void {
    localStorage.removeItem('JWToken');
    localStorage.removeItem('Person');
  }

  public setLoginStorage(): void {
    localStorage.setItem(this.JWTokenName, this.person.token);
    localStorage.setItem('Person', JSON.stringify(this.person));
  }

  public getPerson(): PersonBusiness {
    return this.person = localStorage.getItem('Person') !== '' ?
      JSON.parse(localStorage.getItem('Person'))
      : '';
  }
}
