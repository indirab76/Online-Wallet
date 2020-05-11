import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminAuthenticationService } from './admin-authentication.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  loginForm: FormGroup;
  loginName:FormControl;
  password:FormControl;
  validLogin:boolean = true

  constructor(builder:FormBuilder, private router:Router, private authenticationService:AdminAuthenticationService) { 
    this.loginName=new FormControl("",[Validators.required]);
    this.password=new FormControl("",[Validators.required]);
    this.loginForm=builder.group({
      loginName:this.loginName,
      password:this.password,
    });
  }

  ngOnInit(): void {
  }
   
  checkLogin() {
    
    (async () => { 
      this.authenticationService.authenticate(this.loginName.value, this.password.value);
      console.log('before delay')
      await this.delay(1000);
      console.log('after delay')
      console.log('adminId befor route------'+sessionStorage.getItem('adminId'));
      if (this.authenticationService.isAdminLoggedIn()){
       
        this.router.navigate(['/adminprofile'])
      } else{
        this.validLogin = false
  
      }
  })();
 
  }
  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
   }
  onRegister(){
    this.router.navigate(['/create'])
  }
}
