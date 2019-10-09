import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ErrorComponent} from '../component/form/error/error.component';
import {SuccessfulComponent} from '../component/form/successful/successful.component';
import {MenuComponent} from '../component/menu/menu.component';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [
    ErrorComponent,
    SuccessfulComponent,
    MenuComponent
  ],
  exports: [
    ErrorComponent,
    SuccessfulComponent,
    MenuComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ]
})
export class SharedModule { }
