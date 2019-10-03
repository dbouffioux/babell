import {TodoInterface} from './todo.interface';

export interface ProjectInterface {
  idProjet?: number;
  name: string;
  projectStart: Date;
  projectEnd: Date;
  todoDtoList: TodoInterface[];
}
