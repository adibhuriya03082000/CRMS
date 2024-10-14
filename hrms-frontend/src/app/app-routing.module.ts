import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

const routes: Routes = [
    {
        path: 'login',
        loadChildren: () => import('./login/login.module').then(d => d.LoginModule)
    },
    {
        path: 'signin',
        loadChildren: () => import('./signup/signup.module').then(d => d.SigninModule)
    }
]
@NgModule({
    imports: [
        RouterModule.forRoot(routes),
        RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule { }