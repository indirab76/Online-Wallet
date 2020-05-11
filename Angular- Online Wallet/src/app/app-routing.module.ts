import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateAccountComponent } from './create-account/create-account.component';
import { LoginComponent } from './login/login.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserLogoutComponent } from './login/user-logout/user-logout.component';
import { UserAuthGuardService } from './login/user-auth-guard.service';
import { AdminProfileComponent } from './admin-profile/admin-profile.component';
import { AdminLogoutComponent } from './login/admin-logout/admin-logout.component';
import { AdminAuthGuardService } from './login/admin-auth-guard.service';
import { ValidateUsersComponent } from './validate-users/validate-users.component';
import { MyWalletComponent } from './my-wallet/my-wallet.component';
import { AddMoneyComponent } from './add-money/add-money.component';
import { TransactionFormComponent } from './transaction-form/transaction-form.component';


const routes: Routes = [
  {path:'',redirectTo:'login',pathMatch:'full'},
  { path: 'login', component: LoginComponent },
  { path: 'create', component: CreateAccountComponent },
  { path: 'userprofile', component: UserProfileComponent,canActivate:[UserAuthGuardService] },
  { path: 'mywallet', component: MyWalletComponent,canActivate:[UserAuthGuardService] },
  { path: 'userlogout', component: UserLogoutComponent,canActivate:[UserAuthGuardService] },
  { path: 'addmoney', component: AddMoneyComponent,canActivate:[UserAuthGuardService] },
  { path: 'transfermoney', component: TransactionFormComponent,canActivate:[UserAuthGuardService] },
  { path: 'adminprofile', component: AdminProfileComponent,canActivate:[AdminAuthGuardService] },
  { path: 'validateRegisterdUsers', component: ValidateUsersComponent,canActivate:[AdminAuthGuardService] },
  { path: 'adminlogout', component: AdminLogoutComponent,canActivate:[AdminAuthGuardService] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
