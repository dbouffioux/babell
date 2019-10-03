import {ProjectInterface} from '../project.interface';
import {BusinessInterface} from '../../../../model/business.interface';
import {TodoBusiness} from './todo.business';
import {TodoInterface} from '../todo.interface';

export class ProjectBusiness implements BusinessInterface {

  public readonly id: number;
  public name: string;
  public startDate: Date;
  public endDate: Date;
  public todoList: TodoInterface[];

  constructor(id: number, name: string, startDate: Date, endDate: Date, todoList: TodoInterface[]) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.todoList = todoList;
  }

  fromDto(projectInterface: ProjectInterface): ProjectBusiness  {
    return new ProjectBusiness(
      projectInterface.idProjet,
      projectInterface.name,
      projectInterface.startDate,
      projectInterface.endDate,
      projectInterface.todoList
    );
  }

  toDto(): ProjectInterface {
    return {
      idProjet : this.id,
      name: this.name,
      startDate: this.startDate,
      endDate: this.endDate,
      todoList: this.todoList
    };
  }
}
