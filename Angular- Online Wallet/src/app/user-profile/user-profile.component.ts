import { Component, OnInit } from "@angular/core";
import { User } from "./user";
import {
  FormGroup,
  FormControl,
  FormBuilder,
  Validators,
} from "@angular/forms";
import { UserProfileService } from "./user-profile.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-user-profile",
  templateUrl: "./user-profile.component.html",
  styleUrls: ["./user-profile.component.css"],
})
export class UserProfileComponent implements OnInit {
  user: User = new User();
  editMode: boolean = false;
  showMsg: boolean = false;
  showStatus: number = 3;
  deleteMsg: any;
  deleteStatus: boolean = false;

  updateForm: FormGroup;

  userName: FormControl;
  phoneNumber: FormControl;
  aadhaarNo: FormControl;
  loginName: FormControl;
  password: FormControl;

  /** Initialize FormGroup and FormControls and disable the formcontrols if editMode is true*/
  constructor(
    builder: FormBuilder,
    private service: UserProfileService,
    private router: Router
  ) {
    this.loginName = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,
    ]);
    this.password = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,
      Validators.minLength(8),
    ]);
    this.aadhaarNo = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,
      Validators.min(200000000000),
      Validators.max(999999999999),
    ]);
    this.phoneNumber = new FormControl(
      { value: "", disabled: !this.editMode },
      [
        Validators.required,
        Validators.min(1000000000),
        Validators.max(9999999999),
      ]
    );
    this.userName = new FormControl({ value: "", disabled: !this.editMode }, [
      Validators.required,
    ]);

    this.updateForm = builder.group({
      loginName: this.loginName,
      password: this.password,
      aadhaarNo: this.aadhaarNo,
      phoneNumber: this.phoneNumber,
      userName: this.userName,
    });
  }

  ngOnInit(): void {
    this.onReloadData();
  }
  /** Reloads UserProfile data from userId and sets value into form controls */
  onReloadData() {
    (async () => {
      this.service.searchUser(sessionStorage.getItem("userId")).subscribe(
        (data) => {
          this.user = data;
        },
        (error) => console.log(error)
      );

      await this.delay(1000);

      console.log("user data------" + this.user);
      this.updateForm.get("loginName").setValue(this.user.loginName);
      this.updateForm.get("userName").setValue(this.user.userName);
      this.updateForm.get("password").setValue(this.user.password);
      this.updateForm.get("aadhaarNo").setValue(this.user.aadhaarNo);
      this.updateForm.get("phoneNumber").setValue(this.user.phoneNumber);

      /** messages for 'registered' and 'rejected' users */
      if (sessionStorage.getItem("userStatus") == "registered")
        this.showStatus = 1;
      else if (sessionStorage.getItem("userStatus") == "rejected")
        this.showStatus = 2;
      else this.showStatus = 3;
    })();
  }

  /** Enables the form controls in edit mode */
  onEdit() {
    this.editMode = true;
    this.updateForm.get("userName").enable();
    this.updateForm.get("loginName").enable();
    this.updateForm.get("password").enable();
    this.updateForm.get("aadhaarNo").enable();
    this.updateForm.get("phoneNumber").enable();
  }

  /** Updates UserProfile data */
  onUpdate() {
    this.user.aadhaarNo = this.aadhaarNo.value;
    this.user.loginName = this.loginName.value;
    this.user.password = this.password.value;
    this.user.phoneNumber = this.phoneNumber.value;
    this.user.userName = this.userName.value;

    /** Status for a 'rejected' user changes to 'registered' on updation */
    if (sessionStorage.getItem("userStatus") == "rejected")
      this.user.walletAccount.status = "registered";

    console.log(this.user);
    (async () => {
      this.service.updateUser(this.user).subscribe(
        (data) => {
          this.showMsg = true;
        },
        (error) => console.log(error)
      );

      await this.delay(1000);

      this.user = new User();
      this.onCancel();
    })();
  }
  /** Cancels updation, returns form to it's previous state */
  onCancel() {
    this.onReloadData();
    this.editMode = false;
    this.updateForm.get("userName").disable();
    this.updateForm.get("loginName").disable();
    this.updateForm.get("password").disable();
    this.updateForm.get("aadhaarNo").disable();
    this.updateForm.get("phoneNumber").disable();
  }
  /** Redirects to logout on clodse of delete modal */
  onClose() {
    this.router.navigate(["/userlogout"]);
  }
  /** Deletes User Account as well as Wallet Account */
  onDelete() {
    (async () => {
      this.service
        .deleteUser(
          sessionStorage.getItem("userId"),
          sessionStorage.getItem("accountId")
        )
        .subscribe(
          (data) => {
            this.deleteMsg = data;
          },
          (error) => console.log(error)
        );

      await this.delay(1000);

      if (this.deleteMsg == "user removed") {
        this.deleteStatus = true;
      } else this.deleteStatus = false;
    })();
  }
  delay(ms: number) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }
}
