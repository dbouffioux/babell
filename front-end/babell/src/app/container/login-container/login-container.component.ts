import { Component, OnInit } from '@angular/core';
import {LoginBusiness} from '../../model/business/login.business';
import {LoginService} from '../../service/login.service';
import {AuthenticationService} from '../../service/authentication.service';

@Component({
  selector: 'app-login-container',
  templateUrl: './login-container.component.html',
  styleUrls: ['./login-container.component.scss']
})
export class LoginContainerComponent implements OnInit {

  public error: string;
  public isLogged: boolean;

  constructor(
    private loginService: LoginService,
    private auth: AuthenticationService
  ) { }

  ngOnInit() {
    this.isLogged = this.auth.isLogged();
  }

  submitLoginForm(formValues: any): void {
    this.loginService.getConnection(
      formValues.email,
      formValues.password
    ).subscribe(
      (loginBusiness: LoginBusiness) => {
        this.auth.setLoginStorage(loginBusiness);
        this.auth.setUser(formValues.email);
      },
      error => {
        this.error = error.message;
      }
    );
  }
}
