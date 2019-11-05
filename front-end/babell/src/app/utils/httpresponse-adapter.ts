/**
 * Adapter that get ResponseInterface<T> payload and
 * map to a specific Business class from its specific DTO.
 */
import {ResponseInterface} from '../model/response.interface';
import { StateContext } from '@ngxs/store';
import { HttpErrorResponse } from '@angular/common/http';
import { ProjectStateModel } from '../store/project-state';

export enum HTTPResponseStatuses {
  SUCCESS = 'SUCCESS'
}

export class HTTPResponseAdapter {

  static adapt<T>(responseObject: any, className: any): ResponseInterface<T> {
    responseObject.payload = Array.isArray(responseObject.payload) ?
      responseObject.payload.map((obj: any) => {
        return (new className()).fromDto(obj);
      })
      : (new className()).fromDto(responseObject.payload);
    return responseObject;
  }
}
