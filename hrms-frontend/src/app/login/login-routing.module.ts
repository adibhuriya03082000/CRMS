import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login.component";
import { NgModule } from "@angular/core";

const rotes: Routes = [
    {
        path: '',
        component: LoginComponent
    }
]
@NgModule({
    imports: [RouterModule.forChild(rotes)],
    exports: [RouterModule]
})
export class LoginRouting { }