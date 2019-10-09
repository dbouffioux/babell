import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ErrorComponent} from '../component/form/error/error.component';
import {SuccessfulComponent} from '../component/form/successful/successful.component';

@NgModule({
  declarations: [
    ErrorComponent,
    SuccessfulComponent
  ],
  exports: [
    ErrorComponent,
    SuccessfulComponent
  ],
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
