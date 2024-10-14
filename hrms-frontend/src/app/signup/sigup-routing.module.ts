import { RouterModule, Routes } from "@angular/router";
import { SigninComponent } from "./signup.component";
import { NgModule } from "@angular/core";

const rotes: Routes = [
    {
        path: '',
        component: SigninComponent
    }
]
@NgModule({
    imports: [RouterModule.forChild(rotes)],
    exports: [RouterModule]
})
export class LoginRouting { }