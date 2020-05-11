import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-wallet',
  templateUrl: './my-wallet.component.html',
  styleUrls: ['./my-wallet.component.css']
})
export class MyWalletComponent implements OnInit {
  accountStatus=sessionStorage.getItem('userStatus');
  
  constructor() { }

  ngOnInit(): void {
    console.log(this.accountStatus);
  }

}
