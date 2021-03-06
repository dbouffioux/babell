import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../../service/auth/authentication.service';
import {ValidationService} from '../../../service/validation.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {

  public loginForm: FormGroup;
  @Input() public error: string;
  @Output() private loginFormEmitter = new EventEmitter();

  constructor(
    private fb: FormBuilder
  ) {
    this.loginForm = this.fb.group({
      email: this.fb.control('', [Validators.required, Validators.email]),
      password: this.fb.control('', [
        Validators.required,
        ValidationService.passwordValidator])
    });
  }

  submitForm() {
    this.loginFormEmitter.emit(this.loginForm.value);
  }
}
