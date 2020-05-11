import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthenticationService } from './user-authentication.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  loginForm: FormGroup;
  loginName:FormControl;
  password:FormControl;
  validLogin:boolean = true

  constructor(builder:FormBuilder, private router:Router, private authenticationService:UserAuthenticationService) { 
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
      console.log('userId befor route------'+sessionStorage.getItem('userId'));
      if (this.authenticationService.isUserLoggedIn()){
       
        this.router.navigate(['/userprofile'])
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
