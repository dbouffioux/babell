import {ProjectInterface} from '../project.interface';

export class ProjectBusiness {
  public readonly id: number; // readonly = can be set only in constructor
  public name: string;
  public startDate: Date;
  public endDate: Date;

  constructor(id: number, name: string, startDate: Date, endDate: Date) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public static fromDTO(projectInterface: ProjectInterface): ProjectBusiness  {
    return new ProjectBusiness(
      projectInterface.idProjet,
      projectInterface.name,
      projectInterface.startDate,
      projectInterface.endDate
    );
  }

  public toDTO(): ProjectInterface {
    return {
      idProjet : this.id,
      name: this.name,
      startDate: this.startDate,
      endDate: this.endDate
    };
  }
}
