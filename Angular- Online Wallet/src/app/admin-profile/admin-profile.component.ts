import { Component, OnInit } from '@angular/core';
import { User } from '../user-profile/user';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { AdminProfileService } from './admin-profile.service';
import { Router } from '@angular/router';
import { Admin } from './admin';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent implements OnInit {

 admin:Admin=new Admin();
 editMode:boolean=false;
 showMsg:boolean=false;
 deleteMsg:any;
 deleteStatus:boolean=false;

 updateForm: FormGroup;

  adminName:FormControl;
  phoneNumber:FormControl;
  aadhaarNo:FormControl;
  loginName:FormControl;
  password:FormControl;

  constructor(builder:FormBuilder, private service:AdminProfileService,private router:Router) {
    this.loginName=new FormControl({value:'' , disabled:!this.editMode},[Validators.required]);
    this.password=new FormControl({value:'' , disabled: !this.editMode}, [Validators.required , Validators.minLength(8)]);
    this.aadhaarNo=new FormControl({value:'' , disabled: !this.editMode},[Validators.required, Validators.min(200000000000), Validators.max(999999999999)]);
    this.phoneNumber=new FormControl({value:'', disabled:!this.editMode},[Validators.required,Validators.min(1000000000), Validators.max(9999999999)]);
    this.adminName=new FormControl({value: '', disabled: !this.editMode},[Validators.required]);
    
    this.updateForm=builder.group({
      loginName:this.loginName,
      password:this.password,
      aadhaarNo:this.aadhaarNo,
      phoneNumber:this.phoneNumber,
      adminName:this.adminName
    });

    console.log(this.editMode)
  }
  

  ngOnInit(): void {
    this.onReloadData();
  }
  
  onReloadData(){
    (async () => { 
    this.service.searchAdmin(sessionStorage.getItem('adminId')).subscribe(
      data=>{
 
       this.admin=data;
      },
      error=>console.log(error)
    );

      await this.delay(1000);

    this.updateForm.get('loginName').setValue(this.admin.loginName)
    this.updateForm.get('adminName').setValue(this.admin.adminName)
    this.updateForm.get('password').setValue(this.admin.password)
    this.updateForm.get('aadhaarNo').setValue(this.admin.aadhaarNo)
    this.updateForm.get('phoneNumber').setValue(this.admin.phoneNumber)
    }
    )();
  }
  onEdit(){
    this.editMode=true;
    this.updateForm.get('adminName').enable();
    this.updateForm.get('loginName').enable();
    this.updateForm.get('password').enable();
    this.updateForm.get('aadhaarNo').enable();
    this.updateForm.get('phoneNumber').enable();
  }
  onUpdate(){
    this.admin.aadhaarNo = this.aadhaarNo.value;
    this.admin.loginName = this.loginName.value;
    this.admin.password = this.password.value;
    this.admin.phoneNumber = this.phoneNumber.value;
    this.admin.adminName = this.adminName.value;

    (async () => { 
    this.service.updateAdmin(this.admin).subscribe(
      data=>{
        this.showMsg=true
        error=>console.log(error)   
      });

      await this.delay(1000);
    
      this.admin=new Admin();
      this.onCancel();
    }
      )();
  }
  onCancel(){
    this.onReloadData();
    this.editMode=false;
    this.updateForm.get('adminName').disable();
    this.updateForm.get('loginName').disable();
    this.updateForm.get('password').disable();
    this.updateForm.get('aadhaarNo').disable();
    this.updateForm.get('phoneNumber').disable();
  }
  onClose(){
    this.router.navigate(['/logout'])
  }
  onDelete(){
    (async () => { 
      this.service.deleteAdmin(sessionStorage.getItem('adminId')).subscribe(
        data=>{
          this.deleteMsg = data;
        },
        error=>console.log(error)
     );
      
      await this.delay(1000);
  
      if(this.deleteMsg == "admin removed"){

        this.deleteStatus=true
        
        }
        else
        this.deleteStatus=false
  })();
  }
  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
   }
  
}
