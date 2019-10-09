import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PersonInterface} from '../../model/person.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  @Input() public error: string;
  @Input() public isAuthenticated: boolean;
  @Output() private loginFormEmitter = new EventEmitter();
  @Output() private subscriptionFormEmitter = new EventEmitter<PersonInterface>();

  constructor() { }

  ngOnInit() {
  }

  submitLoginForm(formValues: any): void {
    this.loginFormEmitter.emit(formValues);
  }

  submitSubscriptionForm(person: PersonInterface): void  {
    this.subscriptionFormEmitter.emit(person);
  }
}
