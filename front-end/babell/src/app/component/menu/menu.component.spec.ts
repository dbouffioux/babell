import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MenuComponent } from './menu.component';
import { RouterLink, Router } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Location } from '@angular/common';
import { By } from '@angular/platform-browser';
import { AuthenticationService } from 'src/app/service/auth/authentication.service';
import { LoginBusiness } from 'src/app/model/business/login.business';

/*
class FakeAuthService {

  constructor() {
  }

  public setAuthentication(): void {
    localStorage.setItem('JWToken', 'ok');
  }

  public getToken(): string {
    return localStorage.getItem('JWToken');
  }

  public isAuthenticated(): boolean {
    return !!this.getToken();
  }

  public removeLoginStorage(): void {
    localStorage.removeItem('JWToken');
  }
}*/

describe('MenuComponent', () => {
  let component: MenuComponent;
  let fixture: ComponentFixture<MenuComponent>;
  let location: Location;
  let store: any;
  let auth: AuthenticationService;
  let router: Router;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        MenuComponent
      ],
      imports: [
        RouterTestingModule,
        HttpClientTestingModule
      ],
      providers: [
        AuthenticationService
       /* { // To use another service and replace it by a faked one
          provide: AuthenticationService, // what my component needs (dependency injection)
          useClass: FakeAuthService // what it should use instead
        }*/
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    location = TestBed.get(Location);
    router = TestBed.get(Router);
    // store = {};
    auth = TestBed.get(AuthenticationService);

    const loginBusiness: LoginBusiness = {
      token: 'ok',
      fromDto: null,
      toDto: null
    };

    // set login storage
    auth.setLoginStorage(loginBusiness);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show log out button if user is authenticated', async () => {

    // go here to reload the store
    await fixture.ngZone.run(() => router.navigate(['/']));
    // detect component changes in angular zone
    await fixture.ngZone.run(() => fixture.detectChanges());

    expect(component.isAuthenticated).toBeTruthy();

    const link = fixture.debugElement.query(By.css('#logout')).nativeElement as HTMLLinkElement;
    const spy = spyOn(router, 'navigateByUrl');

    // trigger link click, then checks if it was triggered
    link.click();
    expect(spy.calls.count()).toBe(1);

    // check if url is the good one
    const url = spy.calls.first().args[0].toString();
    expect(url).toContain('/login');
  });

  it('should NOT show log out button if user is not authenticated', async () => {

    await fixture.ngZone.run(() => auth.removeLoginStorage());
    // go here to reload the store
    await fixture.ngZone.run(() => router.navigate(['/']));
    // detect component changes in angular zone
    await fixture.ngZone.run(() => fixture.detectChanges());

    expect(auth.isAuthenticated()).toBeFalsy();

    expect(fixture.debugElement.query(By.css('#logout'))).toBeNull();
  });
});
