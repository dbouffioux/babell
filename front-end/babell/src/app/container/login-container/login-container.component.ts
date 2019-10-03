import { Component, OnInit } from '@angular/core';
import {LoginBusiness} from '../../model/business/login.business';
import {LoginService} from '../../service/auth/login.service';
import {AuthenticationService} from '../../service/auth/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-container',
  templateUrl: './login-container.component.html',
  styleUrls: ['./login-container.component.scss']
})
export class LoginContainerComponent implements OnInit {

  public error: string;
  public isAuthenticated: boolean;

  constructor(
    private loginService: LoginService,
    private auth: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.isAuthenticated = this.auth.isAuthenticated();
  }

  submitLoginForm(formValues: any): void {
    this.loginService.getConnection(
      formValues.email,
      formValues.password
    ).subscribe(
      (loginBusiness: LoginBusiness) => {
        if (loginBusiness) {
          this.auth.setLoginStorage(loginBusiness);
          this.auth.setUser(formValues.email);
          this.router.navigate(['/projects']).then(r => (r));
        } else {
          this.error = 'Unknown error';
        }
      },
      error => {
        this.error = error.message;
      }
    );
  }
}
