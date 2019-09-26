import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpEvent, HttpHandler, HttpRequest} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {mocks} from '../mock/mock';
import {environment} from '../../environments/environment';

@Injectable({providedIn: 'root'})
export class MockHttpInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const foundMock = this.isFound(req);

    if (foundMock) {
      return this.executeMock(req, foundMock.response);
    } else {
      return next.handle(req);
    }
  }

  /**
   * return the mock responding this request
   * @param request current request
   */
  private isFound(request: HttpRequest<any>) {
    const url: string = request.url;
    const method: string = request.method;
    return mocks.find(mock => { // quand on fait un find le premier est retourné uniquement
      return ((new RegExp(mock.url).test(url)) &&
        (method === mock.methods) &&
        (environment.mock.services[mock.name] || environment.mock.all));
    });
  }

  private executeMock(request, response): any {
    const result = response(request);
    if (result instanceof Observable) {
      return result;
    }
    return of(result);
  }
}
