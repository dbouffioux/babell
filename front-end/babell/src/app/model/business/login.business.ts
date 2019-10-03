import {BusinessInterface} from '../business.interface';
import {LoginInterface} from '../login.interface';

export class LoginBusiness implements BusinessInterface {

  public token: string;
  // public refreshToken: string;

  constructor(token: string /*, refreshToken: string*/) {
    this.token = token;
    // this.refreshToken = refreshToken;
  }

  fromDto(loginInterface: LoginInterface): LoginBusiness  {
    return new LoginBusiness(
      loginInterface.token,
      // loginInterface.refreshToken
    );
  }

  toDto(): LoginInterface {
    return {
      token: this.token,
      // refreshToken: this.refreshToken
    };
  }
}

