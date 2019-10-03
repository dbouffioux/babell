import {BusinessInterface} from '../../../../model/business.interface';
import {TodoInterface} from '../todo.interface';

export class TodoBusiness implements BusinessInterface {
  public id?: number;
  public name: string;
  public description: string;
  public estimation?: number;
  public inProgress: boolean;
  public done?: boolean;

  constructor(id: number, name: string, description: string, estimation: number, inProgress: boolean, done: boolean) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.estimation = estimation;
    this.inProgress = inProgress;
    this.done = done;
  }

  fromDto(interfaceDto: TodoInterface): TodoBusiness {
    return new TodoBusiness(
      interfaceDto.id,
      interfaceDto.name,
      interfaceDto.description,
      interfaceDto.estimation,
      interfaceDto.inProgress,
      interfaceDto.done
    );
  }

  toDto(): TodoInterface {
    return {
      id: this.id,
      name: this.name,
      description: this.description,
      estimation: this.estimation,
      inProgress: this.inProgress,
      done: this.done
    };
  }
}
