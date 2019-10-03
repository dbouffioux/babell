/**
 * Adapter that get ResponseInterface<T> payload and
 * map to a specific Business class from its specific DTO.
 */
export class HTTPResponseAdapter {
  static adapt(responseObject: any, className: any): any[] | any {
    return Array.isArray(responseObject.payload) ?
      responseObject.payload.map((obj: any) => {
        return (new className()).fromDto(obj);
      }) : (new className()).fromDto(responseObject.payload);
  }
}
