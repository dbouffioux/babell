import {Component, Input, OnInit} from '@angular/core';
import {AuthenticationService} from '../../service/auth/authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  public isAuthenticated: boolean;

  constructor(
    private auth: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.setIsAuthenticated();
  }

  closeConnection() {
    this.auth.removeLoginStorage();
    this.setIsAuthenticated();
    this.router.navigate(['/login']).then(r => (r));
  }

  public setIsAuthenticated() {
    this.isAuthenticated = this.auth.isAuthenticated();
  }
}
