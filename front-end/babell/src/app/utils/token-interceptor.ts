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

  // private isRefreshing = false;
  /* BehaviorSubject is a Subject, it is a special type of Observable that allows values to be
     multicasted to many Observables. Subjects are like EventEmitters.
     BehaviorSubject is a variant of Subject that requires an initial value and emits its current
     value whenever it is subscribed to.
  */
  // private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null); // initialized to null

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

  /*private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
    if (!this.isRefreshing && thi) {
      // prevent parallel token refreshing (potential infinite loop)
      this.isRefreshing = true;
      // set value to null while refreshing
      this.refreshTokenSubject.next(null);
      // get new token from server
      return this.auth.refreshToken().pipe(
        switchMap((loginBusiness: LoginBusiness) => {
          this.isRefreshing = false;
          this.refreshTokenSubject.next(loginBusiness.token);
          // block http request until we add token to request handler
          // > we tell the interceptor via next handler that we are done w/ request processing
          return next.handle(TokenInterceptor.addToken(request, loginBusiness.token));
        }));
    } else {
      // if this is refreshing
      return this.refreshTokenSubject.pipe(
        filter(token => token != null), // wait until refreshTokenSubject contains value other than null
        take(1), // returns Observable that emit the first value (because of arg: 1)
        switchMap(jwt => { // forget previous value when the new one arrives
          // block http request until we add token to request handler
          // > we tell the interceptor via next handler that we are done w/ request processing
          return next.handle(TokenInterceptor.addToken(request, jwt));
        }));
      // SO IN CASE OF REFRESHING : we wait for token to be different than null if we are refreshing,
      // then we take that value and forget previous ones,
      // finally we add token to next http request that we handle.
    }
  }*/

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
