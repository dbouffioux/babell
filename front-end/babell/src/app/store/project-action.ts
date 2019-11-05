import { ProjectBusiness } from '../module/project/model/business/project.business';
import { TodoInterface } from '../module/project/model/todo.interface';
import { TodoBusiness } from '../module/project/model/business/todo.business';

// tslint:disable-next-line: no-namespace
export namespace ProjectAction {

  export class LoadList {
    static readonly type = `[Project List] Load the list of projects`;
  }

  export class LoadProject {
    static readonly type = `[Project Page] Load a project`;
    constructor(public name: string) {}
  }

  export class AddTodo {
    static readonly type = '[Todo API] Add a todo';
    constructor(
      public todo: TodoBusiness,
      public projectName: string) {
    }
  }
}
