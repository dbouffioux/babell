/**
 * Adapter that get ResponseInterface<T> payload and
 * map to a specific Business class from its specific DTO.
 */
export class HTTPResponseAdapter {
  static adapt(responseObject: any, className: any): any[] | any {
    // if response changes, I only need to change it here. Ez.
    return  responseObject.payload.map((project: any) => {
      return (new className()).fromDto(project);
    });
  }
}
