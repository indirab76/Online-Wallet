import { Component, OnInit } from '@angular/core';
import { UserAuthenticationService } from '../login/user-login/user-authentication.service';
import { AdminAuthenticationService } from '../login/admin-login/admin-authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor( public userloginService:UserAuthenticationService, public adminloginService:AdminAuthenticationService) { }

  ngOnInit(): void {
  }

}
