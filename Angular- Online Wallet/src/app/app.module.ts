import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateAccountComponent } from './create-account/create-account.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AdminProfileComponent } from './admin-profile/admin-profile.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { AdminLoginComponent } from './login/admin-login/admin-login.component';
import { UserLoginComponent } from './login/user-login/user-login.component';
import { UserLogoutComponent } from './login/user-logout/user-logout.component';
import { AdminLogoutComponent } from './login/admin-logout/admin-logout.component';
import { ValidateUsersComponent } from './validate-users/validate-users.component';
import { MyWalletComponent } from './my-wallet/my-wallet.component';
import { ShowBalanceComponent } from './my-wallet/show-balance/show-balance.component';
import { MyTransactionsComponent } from './my-wallet/my-transactions/my-transactions.component';
import { TransactionItemComponent } from './my-wallet/my-transactions/transaction-item/transaction-item.component';
import { AddMoneyComponent } from './add-money/add-money.component';
import { TransactionFormComponent } from './transaction-form/transaction-form.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateAccountComponent,
    UserProfileComponent,
    AdminProfileComponent,
    LoginComponent,
    HeaderComponent,
    UserLoginComponent,
    AdminLoginComponent,
    UserLogoutComponent,
    AdminLogoutComponent,
    ValidateUsersComponent,
    MyWalletComponent,
    ShowBalanceComponent,
    MyTransactionsComponent,
    TransactionItemComponent,
    AddMoneyComponent,
    TransactionFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
