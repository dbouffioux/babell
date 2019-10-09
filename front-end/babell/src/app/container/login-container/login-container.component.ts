import { Component, OnInit } from '@angular/core';
import {LoginBusiness} from '../../model/business/login.business';
import {LoginService} from '../../service/auth/login.service';
import {AuthenticationService} from '../../service/auth/authentication.service';
import {Router} from '@angular/router';
import {LoginInterface} from '../../model/login.interface';
import {ResponseInterface} from '../../model/response.interface';

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
      (response: ResponseInterface<LoginBusiness>) => {
        if (response.payload) {
          this.auth.setLoginStorage(response.payload);
          this.auth.setUser(formValues.email);
          this.router.navigate(['/projects']).then(r => (r));
        } else {
          this.error = 'Unknown error';
        }
      },
      error => {
        this.error = 'Combinaison mot de passe / nom d\'utilisateur incorrecte';
      }
    );
  }
}
