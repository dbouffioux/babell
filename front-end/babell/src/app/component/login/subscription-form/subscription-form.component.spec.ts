import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscriptionFormComponent } from './subscription-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { HttpClient } from '@angular/common/http';
import { ValidationService } from 'src/app/service/validation.service';
import { PersonInterface } from 'src/app/model/person.interface';

describe('SubscriptionFormComponent', () => {
  let component: SubscriptionFormComponent;
  let fixture: ComponentFixture<SubscriptionFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubscriptionFormComponent ],
      imports: [
        ReactiveFormsModule,
        HttpClientTestingModule
      ],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscriptionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should not be valid if inputs are empty', () => {
    // fill form with bad values
    component.subscriptionForm.patchValue({
      firstname: '',
      lastname: '',
      email: '',
      password: ''
    });

    expect(component.subscriptionForm.valid).toBeFalsy();
  });

  it('should not be valid if password is filled and do not respect regex', () => {
    component.subscriptionForm.patchValue({
      password: '---'
    });

    const password = component.subscriptionForm.controls.password;

    expect(ValidationService.passwordValidator(password)).not.toBeNull();
    expect(component.subscriptionForm.valid).toBeFalsy();
  });

  it('should be valid if inputs are filled do respect form validation', () => {
    component.subscriptionForm.patchValue({
      firstname: 'Dam',
      lastname: 'Budu',
      email: 'damien@mail.be',
      password: 'test123'
    });

    const firstname = component.subscriptionForm.controls.firstname;
    const lastname = component.subscriptionForm.controls.lastname;
    const password = component.subscriptionForm.controls.password;

    expect(ValidationService.passwordValidator(component.subscriptionForm.controls.password)).toBeNull();
    expect(component.subscriptionForm.valid).toBeTruthy();
  });

  it('#getPersonFromformValues should return a PersonInterface', () => {
    component.subscriptionForm.patchValue({
      firstname: 'Dam',
      lastname: 'Budu',
      email: 'damien@mail.be',
      password: 'test123'
    });

    const personI: PersonInterface = (component as any).getPersonFromFormValues();
    const expected: PersonInterface = {
      id: null,
      firstname: 'Dam',
      lastname: 'Budu',
      email: 'damien@mail.be',
      password: 'test123'
    };

    expect(JSON.stringify(personI) === JSON.stringify(expected)).toBeTruthy();
  });
});
