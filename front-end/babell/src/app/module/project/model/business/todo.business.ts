import {BusinessInterface} from '../../../../model/business.interface';
import {TodoInterface} from '../todo.interface';

export class TodoBusiness implements BusinessInterface {
  public id?: number;
  public name: string;
  public description: string;
  public estimation?: number;
  public inProgress?: boolean;
  public done?: boolean;
  public projectId?: number;

  constructor(id: number, name: string, description: string, estimation: number, inProgress: boolean, done: boolean, projectId: number) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.estimation = estimation;
    this.inProgress = inProgress;
    this.done = done;
    this.projectId = projectId;
  }

  fromDto(interfaceDto: TodoInterface): TodoBusiness {
    return new TodoBusiness(
      interfaceDto.id,
      interfaceDto.name,
      interfaceDto.description,
      interfaceDto.estimation,
      interfaceDto.inProgress,
      interfaceDto.done,
      interfaceDto.projectId
    );
  }

  toDto(): TodoInterface {
    return {
      id: this.id,
      name: this.name,
      description: this.description,
      estimation: this.estimation,
      inProgress: this.inProgress,
      done: this.done,
      projectId : this.projectId
    };
  }
}
