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
  reloadData() {
    console.log("reload data");
    this.userlist = this.service.showRegisteredUsers();
    console.log(this.userlist);
    this.acceptedlist = this.service.showAcceptedUsers();
    console.log(this.acceptedlist);
  }

  reject(id: number) {
    (async () => {
      this.service.updateStatus(id, this.rejected).subscribe(
        (data) => {
          console.log(data);
        },
        (error) => console.log(error)
      );
      console.log("before delay");
      await this.delay(500);
      console.log("after delay");
      this.reloadData();
    })();
  }

  accept(id: number) {
    (async () => {
      this.service.updateStatus(id, this.accepted).subscribe(
        (data) => {
          console.log(data);
        },
        (error) => console.log(error)
      );
      console.log("before delay");
      await this.delay(500);
      console.log("after delay");
      this.reloadData();
    })();
  }

  clear(id: number) {
    this.service.updateStatus(id, this.registered).subscribe(
      (data) => {
        console.log(data);
      },
      (error) => console.log(error)
    );
  }
  delay(ms: number) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }
}
