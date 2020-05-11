import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { UserAuthenticationService } from './user-login/user-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UserAuthGuardService {

  constructor(private router: Router,
    private authService: UserAuthenticationService) { }
    
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.isUserLoggedIn())
      return true;

    this.router.navigate(['login']);
    return false;

  }
}
