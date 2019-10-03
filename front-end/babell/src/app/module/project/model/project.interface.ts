import {TodoInterface} from './todo.interface';

export interface ProjectInterface {
  idProjet?: number;
  name: string;
  startDate: Date;
  endDate: Date;
  todoList: TodoInterface[];
}
