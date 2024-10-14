import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SigninComponent } from './signup.component';



@NgModule({
  declarations: [SigninComponent],
  imports: [
    CommonModule,
    SigninModule
  ]
})
export class SigninModule { }
