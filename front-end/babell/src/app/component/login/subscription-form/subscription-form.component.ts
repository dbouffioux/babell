import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../../service/auth/authentication.service';
import {ValidationService} from '../../../service/validation.service';
import {PersonBusiness} from '../../../model/business/person.business';
import {PersonInterface} from '../../../model/person.interface';

@Component({
  selector: 'app-subscription-form',
  templateUrl: './subscription-form.component.html',
  styleUrls: ['./subscription-form.component.scss']
})
export class SubscriptionFormComponent implements OnInit {

  public subscriptionForm: FormGroup;
  @Output() private subscriptionFormEmitter = new EventEmitter<PersonInterface>();

  constructor(
    private fb: FormBuilder,
    private auth: AuthenticationService
  ) {
    this.subscriptionForm = this.fb.group({
      firstname: this.fb.control('', [Validators.required]),
      lastname: this.fb.control('', [Validators.required]),
      email: this.fb.control('', [Validators.required, Validators.email]),
      password: this.fb.control('', [
        Validators.required,
        ValidationService.passwordValidator])
    });
  }

  submitForm() {
    const person = this.getPersonFromFormValues();
    this.subscriptionFormEmitter.emit(person);
    // this.subscriptionForm.reset();
  }

  ngOnInit() {
  }

  public getPersonFromFormValues(): PersonInterface {
    const formValues = this.subscriptionForm.value;
    const person = new PersonBusiness(
      null,
      formValues.firstname,
      formValues.lastname,
      formValues.email,
      formValues.password
    );
    return person.toDto();
  }
}
