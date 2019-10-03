import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {environment} from '../environments/environment';
import {MockHttpInterceptor} from './interceptor/mock-http.interceptor';
import {LoginService} from './service/auth/login.service';
import { LoginComponent } from './component/login/login.component';
import { LoginFormComponent } from './component/login/login-form/login-form.component';
import {ReactiveFormsModule} from '@angular/forms';
import { ButtonComponent } from './component/form/button/button.component';
import { ErrorComponent } from './component/form/error/error.component';
import { LoginContainerComponent } from './container/login-container/login-container.component';
import {TokenInterceptor} from './utils/TokenInterceptor';
import {SharedModule} from './shared/shared.module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LoginFormComponent,
    ButtonComponent,
    LoginContainerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    SharedModule
  ],
  providers: [
    environment.mock.enable ? {
      provide: HTTP_INTERCEPTORS,
      useClass: MockHttpInterceptor,
      multi: true
    } : [],
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    LoginService
  ],
  exports: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
