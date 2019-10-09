/**
 * Adapter that get ResponseInterface<T> payload and
 * map to a specific Business class from its specific DTO.
 */
import {ResponseInterface} from '../model/response.interface';

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
