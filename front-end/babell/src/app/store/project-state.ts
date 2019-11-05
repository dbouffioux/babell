import { ProjectBusiness } from '../module/project/model/business/project.business';
import { TodoBusiness } from '../module/project/model/business/todo.business';
import { NgxsOnInit, StateContext, State, Selector } from '@ngxs/store';
import { ProjectService } from '../module/project/service/project.service';
import { TodoService } from '../module/project/service/todo.service';
import { tap } from 'rxjs/operators';
import { ProjectAction } from './project-action';
import { Action } from '@ngxs/store';
import { HTTPResponseStatuses } from '../utils/httpresponse-adapter';

export interface ProjectStateModel {
  projects: ProjectBusiness[];
  selectedProject?: ProjectBusiness;
  error?: string;
  success?: string;
}

@State<ProjectStateModel>({
  name: 'projects',
  defaults: {
    projects: [],
    selectedProject: null,
    error: null,
    success: null
  }
})
export class ProjectState implements NgxsOnInit {

  constructor(
    private projectService: ProjectService,
    private todoService: TodoService) {
  }

  @Selector()
  static projects(state: ProjectStateModel) {
    return state.projects;
  }

  @Selector()
  static selectedProject(state: ProjectStateModel) {
    return state.selectedProject;
  }

  @Selector()
  static error(state: ProjectStateModel) {
    return state.error;
  }

  ngxsOnInit(ctx: StateContext<ProjectStateModel>) {
    // state initialized, now do that
    // example: ctx.dispatch(new Project);
  }

  @Action(ProjectAction.LoadList)
  loadProjects(ctx: StateContext<ProjectStateModel>) {
    return this.projectService.getProjects().pipe(
      tap( response => {
       response.status !== HTTPResponseStatuses.SUCCESS
        ? ctx.patchState({
            error: response.message
          })
        : ctx.patchState({
            success: response.message,
            projects: response.payload
          });
      })
    );
  }

  @Action(ProjectAction.LoadProject)
  getProjectByName(ctx: StateContext<ProjectStateModel>, action: ProjectAction.LoadProject) {
    return this.projectService.getProjectByName(action.name).pipe(
      tap( response => {
       response.status !== HTTPResponseStatuses.SUCCESS
        ? ctx.patchState({
            error: response.message
          })
        : ctx.patchState({
            success: response.message,
            selectedProject: response.payload
          });
      })
    );
  }

  @Action(ProjectAction.AddTodo)
  createTodo(ctx: StateContext<ProjectStateModel>, action: ProjectAction.AddTodo) {
    return this.todoService.createTodo(action.todo, action.projectName).pipe(
      tap( response => {
        if (response.status !== HTTPResponseStatuses.SUCCESS) {
          ctx.patchState({
            error: response.message
          });
        } else {
          ctx.patchState({
            success: response.message,
          });
          ctx.dispatch(new ProjectAction.LoadProject(action.projectName));
        }
      })
    );
  }
}
