import { Component, OnInit } from '@angular/core';
import { UserProfileService } from '../user-profile/user-profile.service';
import { Router } from '@angular/router';
import { User } from '../user-profile/user';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AdminProfileService } from '../admin-profile/admin-profile.service';
import { Admin } from '../admin-profile/admin';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {
  registrationForm: FormGroup;

  userName:FormControl;
  phoneNumber:FormControl;
  aadhaarNo:FormControl;
  loginName:FormControl;
  password:FormControl;
  accType:FormControl;

  user:User=new User();
  admin:Admin=new Admin();
  submitted=false;
  showMsg: boolean = false;
  
   /** Initializing FormControls and FormGroup */
  constructor(builder:FormBuilder, private userservice:UserProfileService,private adminservice:AdminProfileService, private router:Router) {
    this.loginName=new FormControl("",[Validators.required]);
    this.password=new FormControl("",[Validators.required , Validators.minLength(8)]);
    this.aadhaarNo=new FormControl("",[Validators.required , Validators.min(200000000000), Validators.max(999999999999)]);
    this.phoneNumber=new FormControl("",[Validators.required , Validators.min(1000000000), Validators.max(9999999999)]);
    this.userName=new FormControl("",[Validators.required]);
    this.accType=new FormControl('user',[Validators.required]);
    
    this.registrationForm=builder.group({
      loginName:this.loginName,
      password:this.password,
      aadhaarNo:this.aadhaarNo,
      phoneNumber:this.phoneNumber,
      userName:this.userName,
      accType:this.accType
    });
  }
   

  ngOnInit(): void {
  }
 /** Triggered when submit form button is clicked */
  onSubmit(){
    this.submitted=true;
    this.save();
  }
 /**Saves profile details as user or admin according to type selected */
  save(){

    if(this.accType.value == 'user')
    {
      this.user.aadhaarNo = this.aadhaarNo.value;
      this.user.loginName = this.loginName.value;
      this.user.password = this.password.value;
      this.user.phoneNumber = this.phoneNumber.value;
      this.user.userName = this.userName.value;
  
    this.userservice.addUser(this.user).subscribe(
      data=>{
        this.showMsg=true
        ,error=>console.log(error)  
    });
      this.user=new User();
      
  }
  else{

    this.admin.aadhaarNo = this.aadhaarNo.value;
    this.admin.loginName = this.loginName.value;
    this.admin.password = this.password.value;
    this.admin.phoneNumber = this.phoneNumber.value;
    this.admin.adminName = this.userName.value;

    this.adminservice.addAdmin(this.admin).subscribe(
      data=>{
        this.showMsg=true
        ,error=>console.log(error)
        
    });
      this.admin=new Admin();
  }
      
  }
  /**Resets the form on closing of successfull message modal*/
  onClose(){
    this.registrationForm.reset();
    this.showMsg=false;
  }
  /*Routes to Login Page on Sign In*/
  onSignin(){
    this.router.navigate(['/login']);
  }

}
