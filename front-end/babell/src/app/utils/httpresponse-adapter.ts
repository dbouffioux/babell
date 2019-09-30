import {ProjectBusiness} from '../module/project/model/business/project.business';

export class HTTPResponseAdapter {
  static adapt(responseObject: any, className: any): any[] | any {
    return  responseObject.payload.map((project: any) => { // if response changes, I only need to change it here.
      return (new className()).fromDto(project);
    });
  }
}
