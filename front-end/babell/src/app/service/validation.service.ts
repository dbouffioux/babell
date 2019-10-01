import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidationService {

  constructor() { }

  static passwordValidator(control) {

    // {6,100}           - Assert password is between 6 and 100 characters
    // (?=.*[0-9])       - Assert a string has at least one number
    // ?=.* == positive lookahead (means something 1 to x times anywhere)
    if (control.value.match(/^(?=.*[0-9])[a-zA-Z0-9!@#$%^&*]{6,50}$/)) {
      return null;
    } else {
      return { invalidPassword: true };
    }
  }
}
