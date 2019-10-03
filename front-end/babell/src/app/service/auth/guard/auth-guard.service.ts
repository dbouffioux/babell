import { Injectable } from '@angular/core';
import {AuthenticationService} from '../authentication.service';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(
    private auth: AuthenticationService,
    private router: Router
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    // seems unsecure, because local storage can be stealed with XSS .. cookies HttpOnly not, but vulnerable to XCSRF.
    if (this.auth.isAuthenticated()) {
      return true;
    } else {
      this.router.navigate(['/login']).then(r => (r));
      return false;
    }
  }
}
