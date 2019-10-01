import {BusinessInterface} from '../business.interface';
import {LoginInterface} from '../login.interface';

export class LoginBusiness implements BusinessInterface {

  public token: string;

  constructor(token: string) {
    this.token = token;
  }

  fromDto(loginInterface: LoginInterface): LoginBusiness  {
    return new LoginBusiness(
      loginInterface.token
    );
  }

  toDto(): LoginInterface {
    return {
      token: this.token
    };
  }
}

