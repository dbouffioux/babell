import {ProjectInterface} from '../project.interface';
import {BusinessInterface} from '../../../../model/business.interface';
import {TodoBusiness} from './todo.business';

export class ProjectBusiness implements BusinessInterface {

  public readonly id: number;
  public name: string;
  public startDate: Date;
  public endDate: Date;
  public todoList: TodoBusiness[];

  constructor(idProjet: number, name: string, projectStart: Date, projectEnd: Date, todoDtoList: TodoBusiness[]) {
    this.id = idProjet;
    this.name = name;
    this.startDate = projectStart;
    this.endDate = projectEnd;
    this.todoList = todoDtoList;
  }

  fromDto(projectInterface: ProjectInterface): ProjectBusiness  {
    let todoList: TodoBusiness[];
    console.log();
    if (projectInterface.todoDtoList) {
      for (const todoListItem of projectInterface.todoDtoList) {
        // @ts-ignore (unfortunately, i want business to implements businessInterface, so that they have to implements fromDto(),
        // but I must force it to be instance method, because I cant have static method in interface... But I cant overload constructor
        // so I must set ts-ignore.. mmh)
        todoList = [...todoList, (new TodoBusiness()).fromDto(todoListItem)];
      }
    }
    return new ProjectBusiness(
      projectInterface.idProjet,
      projectInterface.name,
      projectInterface.projectStart,
      projectInterface.projectEnd,
      todoList
    );
  }

  toDto(): ProjectInterface {
    return {
      idProjet : this.id,
      name: this.name,
      projectStart: this.startDate,
      projectEnd: this.endDate,
      todoDtoList: this.todoList
    };
  }
}
