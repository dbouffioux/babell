import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginFormComponent } from './login-form.component';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { ReactiveFormsModule, EmailValidator, Validators, FormBuilder } from '@angular/forms';
import { ValidationService } from 'src/app/service/validation.service';

describe('LoginFormComponent', () => {
  let component: LoginFormComponent;
  let fixture: ComponentFixture<LoginFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        LoginFormComponent
       ],
       imports: [
        ReactiveFormsModule
       ],
       schemas: [
        NO_ERRORS_SCHEMA
       ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should not be valid if inputs are empty', () => {
    // fill form with bad values
    component.loginForm.patchValue({
      email: '',
      password: ''
    });

    expect(component.loginForm.valid).toBeFalsy();
  });

  it('should not be valid if password is filled and do not respect regex', () => {
    component.loginForm.patchValue({
      password: '---'
    });

    const password = component.loginForm.controls.password;

    expect(ValidationService.passwordValidator(password)).not.toBeNull();
    expect(component.loginForm.valid).toBeFalsy();
  });

  it('should be valid if inputs are filled do respect form validation', () => {
    component.loginForm.patchValue({
      email: 'damien@mail.be',
      password: 'azerty123'
    });

    const email = component.loginForm.value.email;
    const password = component.loginForm.controls.password;

    expect((new EmailValidator()).validate(email)).toBeNull();
    expect(ValidationService.passwordValidator(component.loginForm.controls.password)).toBeNull();
    expect(component.loginForm.valid).toBeTruthy();
  });
});
