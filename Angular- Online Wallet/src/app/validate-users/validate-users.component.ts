import { Component, OnInit } from "@angular/core";
import { AdminProfileService } from "../admin-profile/admin-profile.service";
import { Router } from "@angular/router";
import { User } from "../user-profile/user";
import { Observable } from "rxjs";

@Component({
  selector: "app-validate-users",
  templateUrl: "./validate-users.component.html",
  styleUrls: ["./validate-users.component.css"],
})
export class ValidateUsersComponent implements OnInit {
  userlist: Observable<User[]>;
  acceptedlist: Observable<User[]>;
  user: User = new User();
  action = null;
  accepted = "accepted";
  rejected = "rejected";
  registered = "registered";
  constructor(private service: AdminProfileService, private router: Router) {}

  ngOnInit(): void {
    this.reloadData();
  }
  /** Reloads Registered UserList and Accepted UserList */
  reloadData() {
  
    this.userlist = this.service.showRegisteredUsers();
    this.acceptedlist = this.service.showAcceptedUsers();
  }

  /** Updates rejected status for user account */
  reject(id: number) {
    (async () => {
      this.service.updateStatus(id, this.rejected).subscribe(
        (data) => {},
        (error) => console.log(error)
      );
   
      await this.delay(500);
     
      this.reloadData();
    })();
  }
/** Reload s accepted status for user account */
  accept(id: number) {
    (async () => {
      this.service.updateStatus(id, this.accepted).subscribe(
        (data) => {},
        (error) => console.log(error)
      );
     
      await this.delay(500);
    
      this.reloadData();
    })();
  }

  delay(ms: number) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }
}
