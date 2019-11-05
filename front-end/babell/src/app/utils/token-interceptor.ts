import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {AuthenticationService} from '../service/auth/authentication.service';
import {BehaviorSubject, Observable, throwError} from 'rxjs';
import {catchError, filter, switchMap, take} from 'rxjs/operators';
import {LoginBusiness} from '../model/business/login.business';
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

/*
* Interceptor adapted from this example :
* https://angular-academy.com/angular-jwt/#application-setup
* Used to intercept http request, and add token to authentify user that makes this request.
* */
@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(
    private auth: AuthenticationService,
    private router: Router
  ) {}

  private static addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // if token exists, add it to http request
    if (this.auth.getToken()) {
      request = TokenInterceptor.addToken(request, this.auth.getToken());
    }
    // then if token does not exist or is invalid, handle error.
    // if error is 401 unauthorized, then force login
    return next.handle(request).pipe(catchError(error => {
      if (error instanceof HttpErrorResponse && error.status === 401) {
        //  return this.handle401Error(request, next);
        this.auth.removeLoginStorage();
        this.router.navigate(['/login']).then(r => (r));
      } else {
        return throwError(error);
      }
    }));
  }
}
