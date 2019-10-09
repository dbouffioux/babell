import {TodoInterface} from './todo.interface';

export interface ProjectInterface {
  idProject?: number;
  name: string;
  projectStart: Date;
  projectEnd: Date;
  todoDtoList: TodoInterface[];
}
