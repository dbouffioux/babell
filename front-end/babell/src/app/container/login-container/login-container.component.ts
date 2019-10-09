import { Component, OnInit } from '@angular/core';
import {LoginBusiness} from '../../model/business/login.business';
import {LoginService} from '../../service/auth/login.service';
import {AuthenticationService} from '../../service/auth/authentication.service';
import {Router} from '@angular/router';
import {LoginInterface} from '../../model/login.interface';
import {ResponseInterface} from '../../model/response.interface';
import {PersonBusiness} from '../../model/business/person.business';
import {PersonInterface} from '../../model/person.interface';

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
    this.getIsAuthenticated();
  }

  submitLoginForm(formValues: any): void {
    this.loginService.getConnection(
      formValues.email,
      formValues.password
    ).subscribe(
      (response: ResponseInterface<LoginBusiness>) => {
        if (response.payload) {
          this.authenticate(response, formValues);
        } else {
          this.error = 'Unknown error';
        }
      },
      error => {
        this.error = 'Combinaison mot de passe / nom d\'utilisateur incorrecte';
      }
    );
  }

  submitSubscriptionForm(person: PersonInterface): void {

    this.loginService.subscription(person).subscribe(
      (response: ResponseInterface<LoginBusiness>) => {
        this.authenticate(response, person);
        },
      error => {
        this.error = 'Combinaison mot de passe / nom d\'utilisateur incorrecte';
      }
    );
  }

  public authenticate(response: ResponseInterface<LoginBusiness>, formValues: any) {
    this.auth.setLoginStorage(response.payload);
    this.auth.setUser(formValues.email);
    this.router.navigate(['/projects']).then(r => (r));
    this.getIsAuthenticated();
  }

  private getIsAuthenticated() {
    this.isAuthenticated = this.auth.isAuthenticated();
  }
}
