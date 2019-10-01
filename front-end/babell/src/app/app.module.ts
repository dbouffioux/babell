import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {environment} from '../environments/environment';
import {MockHttpInterceptor} from './interceptor/mock-http.interceptor';
import {LoginService} from './service/login.service';
import { LoginComponent } from './component/login/login.component';
import { LoginFormComponent } from './component/login/login-form/login-form.component';
import {ReactiveFormsModule} from '@angular/forms';
import { ButtonComponent } from './component/form/button/button.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LoginFormComponent,
    ButtonComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [environment.mock.enable ? {
    provide: HTTP_INTERCEPTORS,
    useClass: MockHttpInterceptor,
    multi: true
  } : [],
  LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
