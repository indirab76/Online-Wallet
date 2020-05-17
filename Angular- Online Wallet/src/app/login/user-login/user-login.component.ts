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
  validLogin:Boolean = true
  submitCalled:Boolean=false;

  /** Initializing FormGroup and FormControls  */
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
   /**Validated User loginName and password. If valid will route to User Profile page. */
  checkLogin() {
    
    (async () => { 
      this.authenticationService.authenticate(this.loginName.value, this.password.value);
      
      await this.delay(1000);
     
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
