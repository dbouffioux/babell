import {ProjectInterface} from '../project.interface';
import {BusinessInterface} from '../../../../model/business.interface';

export class ProjectBusiness implements BusinessInterface {

  public readonly id: number;
  public name: string;
  public startDate: Date;
  public endDate: Date;

  constructor(id: number, name: string, startDate: Date, endDate: Date) {
    this.id = id;
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  fromDto(projectInterface: ProjectInterface): ProjectBusiness  {
    return new ProjectBusiness(
      projectInterface.idProjet,
      projectInterface.name,
      projectInterface.startDate,
      projectInterface.endDate
    );
  }

  toDto(): ProjectInterface {
    return {
      idProjet : this.id,
      name: this.name,
      startDate: this.startDate,
      endDate: this.endDate
    };
  }
}
