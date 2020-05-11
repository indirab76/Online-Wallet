import { Injectable } from '@angular/core';
import { AdminProfileService } from 'src/app/admin-profile/admin-profile.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthenticationService {
  admin_id:any;
  constructor(private service:AdminProfileService,private router:Router,private httpClient: HttpClient) {
    sessionStorage.setItem('adminId', 'null')
    sessionStorage.setItem('type', 'null')
   }

  authenticate(loginName, password) {
       this.service.validLogin(loginName, password).subscribe(
     data=>{
       console.log(data)
      sessionStorage.setItem('adminId', data.adminId)
      sessionStorage.setItem('type', "admin")
   },
     error=>console.log(error)
    );
    
   }

  isAdminLoggedIn() {
    if( sessionStorage.getItem('adminId') == 'null'){
     // console.log('false')
    return false
  }
    else{
     // console.log('true')
    return true
  }
    
  }

  logOut() {
    sessionStorage.setItem('adminId', 'null')
      sessionStorage.setItem('type', 'null')
  }
}
