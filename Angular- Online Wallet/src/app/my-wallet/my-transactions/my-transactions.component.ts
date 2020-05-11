import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../transaction';
import { MyWalletService } from '../my-wallet.service';

@Component({
  selector: 'app-my-transactions',
  templateUrl: './my-transactions.component.html',
  styleUrls: ['./my-transactions.component.css']
})
export class MyTransactionsComponent implements OnInit {
  transactions:Observable<Transaction[]>;

  constructor(private service:MyWalletService) { }

  ngOnInit(): void {
    console.log("reload data");
    this.transactions=this.service.showTransactions(sessionStorage.getItem('accountId'));
    console.log(this.transactions)
  }

}
