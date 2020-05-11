import { Component, OnInit } from '@angular/core';
import { MyWalletService } from '../my-wallet.service';

@Component({
  selector: 'app-show-balance',
  templateUrl: './show-balance.component.html',
  styleUrls: ['./show-balance.component.css']
})
export class ShowBalanceComponent implements OnInit {
  balance
  constructor(private service:MyWalletService) { }

  ngOnInit(): void {
    this.service.showBalance(sessionStorage.getItem('userId')).subscribe(
      data=>{
       console.log(data)
       this.balance=data;
      },
      error=>console.log(error)
    );

  }

}
