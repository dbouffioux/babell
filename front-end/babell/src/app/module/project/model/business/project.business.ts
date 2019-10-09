import {ProjectInterface} from '../project.interface';
import {BusinessInterface} from '../../../../model/business.interface';
import {TodoBusiness} from './todo.business';
import {TodoInterface} from '../todo.interface';

export class ProjectBusiness implements BusinessInterface {

  constructor(idProjet: number, name: string, projectStart: Date, projectEnd: Date, todoDtoList: TodoBusiness[]) {
    this.id = idProjet;
    this.name = name;
    this.startDate = projectStart;
    this.endDate = projectEnd;
    this.todoList = todoDtoList;
  }

  public readonly id: number;
  public name: string;
  public startDate: Date;
  public endDate: Date;
  public todoList: TodoBusiness[];

  private static getTodoListFromDto(todoDtoList: TodoInterface[]): TodoBusiness[] {
    let todoList: TodoBusiness[] = [];
    if (todoDtoList) {
      for (const todoListItem of todoDtoList) {
        // @ts-ignore (unfortunately, i want business to implements businessInterface, so that they have to implements fromDto(),
        // but I must force it to be instance method, because I cant have static method in interface... But I cant overload constructor
        // so I must set ts-ignore.. mmh)
        const todoBusiness = (new TodoBusiness()).fromDto(todoListItem);
        todoList = [todoBusiness, ...todoList];
      }
    }
    return todoList;
  }

  private static getTodoListToDto(todoBusinessList: TodoBusiness[]): TodoInterface[] {
    let todoList: TodoInterface[] = [];
    if (todoBusinessList) {
      for (const todoListItem of todoBusinessList) {
        // @ts-ignore
        const todoBusiness = (new TodoBusiness()).toDto(todoListItem);
        todoList = [todoBusiness, ...todoList];
      }
    }
    return todoList;
  }

  fromDto(projectInterface: ProjectInterface): ProjectBusiness  {
    const todoList = ProjectBusiness.getTodoListFromDto(projectInterface.todoDtoList);
    return new ProjectBusiness(
      projectInterface.idProject,
      projectInterface.name,
      projectInterface.projectStart,
      projectInterface.projectEnd,
      todoList
    );
  }

  toDto(): ProjectInterface {
    return {
      idProject : this.id,
      name: this.name,
      projectStart: this.startDate,
      projectEnd: this.endDate,
      todoDtoList: ProjectBusiness.getTodoListToDto(this.todoList)
    };
  }
}
