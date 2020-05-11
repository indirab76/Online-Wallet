import { Injectable } from '@angular/core';
import { UserProfileService } from 'src/app/user-profile/user-profile.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserAuthenticationService {
  user_id:any;
  private validLoginUrl = "http://localhost:9090/wallet/validLogin";
  constructor(private service:UserProfileService,private router:Router,private httpClient: HttpClient) {
    sessionStorage.setItem('userId', 'null')
   }

  authenticate(loginName, password) {
       this.service.validLogin(loginName, password).subscribe(
     data=>{
       console.log(data)
      this.user_id=data;
      sessionStorage.setItem('userId', data.userId)
      sessionStorage.setItem('userStatus', data.walletAccount.status)
      sessionStorage.setItem('type', "user")
      sessionStorage.setItem('accountId', data.walletAccount.accountId)
   },
     error=>console.log(error)
    );
    
   }

  isUserLoggedIn() {
    if( sessionStorage.getItem('userId') == 'null'){
//console.log('false')
    return false
  }
    else{
     // console.log('true')
    return true
  }
    
  }

  logOut() {
    sessionStorage.setItem('userId', 'null')
    sessionStorage.setItem('userStatus', 'null')
      sessionStorage.setItem('type', 'null')
      sessionStorage.setItem('accountId', 'null')
  }
}
