import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthGuardService} from '../../../service/guard/auth-guard.service';
import {AuthenticationService} from '../../../service/authentication.service';
import {ValidationService} from '../../../service/validation.service';
import {LoginService} from '../../../service/login.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {

  public loginForm: FormGroup;
  public isLogged: boolean;

  constructor(
    private fb: FormBuilder,
    private auth: AuthenticationService,
    private loginService: LoginService
  ) {
    this.loginForm = this.fb.group({
      login: this.fb.control('', [Validators.required]),
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
      formValues.login,
      formValues.password
    );
  }
}
