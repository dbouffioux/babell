import {BusinessInterface} from '../business.interface';
import {PersonInterface} from '../person.interface';

export class PersonBusiness implements BusinessInterface {

  public readonly id: number;
  public firstName: string;
  public lastName: string;
  public email: string;
  public password: string;

  constructor(id: number, firstName: string, lastName: string, email: string, password: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  fromDto(personInterface: PersonInterface): PersonBusiness  {
    return new PersonBusiness(
      personInterface.id,
      personInterface.firstname,
      personInterface.lastname,
      personInterface.email,
      personInterface.password
    );
  }

  toDto(): PersonInterface {
    return {
      id : this.id,
      firstname: this.firstName,
      lastname: this.lastName,
      email: this.email,
      password: this.password
    };
  }
}

