import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../../service/authentication.service';
import {ValidationService} from '../../../service/validation.service';
import {LoginService} from '../../../service/login.service';
import {LoginBusiness} from '../../../model/business/login.business';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {

  public loginForm: FormGroup;
  public isLogged: boolean;
  public error: string;

  constructor(
    private fb: FormBuilder,
    private auth: AuthenticationService,
    private loginService: LoginService
  ) {
    this.error = '';
    this.loginForm = this.fb.group({
      email: this.fb.control('', [Validators.required]),
      password: this.fb.control('', [
        Validators.required,
        ValidationService.passwordValidator])
    });
  }

  ngOnInit() {
    this.isLogged = this.auth.isLogged();
  }

  submitForm() {
    const formValues = this.loginForm.value;
    this.loginService.getConnection(
      formValues.email,
      formValues.password
    ).subscribe(
      (loginBusiness: LoginBusiness) => {
        this.auth.setLoginStorage(loginBusiness);
        this.auth.setUser(formValues.email);
      },
      error => {
        console.log(error);
        this.error = error.message;
      }
    );
  }
}
