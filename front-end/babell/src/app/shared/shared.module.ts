import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ErrorComponent} from '../component/form/error/error.component';

@NgModule({
  declarations: [
    ErrorComponent
  ],
  exports: [
    ErrorComponent
  ],
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
