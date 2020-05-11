import { Component, OnInit, Input } from '@angular/core';
import { MyWalletService } from '../../my-wallet.service';
import { Transaction } from '../../transaction';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-transaction-item',
  templateUrl: './transaction-item.component.html',
  styleUrls: ['./transaction-item.component.css']
})
export class TransactionItemComponent implements OnInit {
  accountName;
  @Input() transact:Transaction;
  @Input() index:number;
  showMsg:number;
  accountId:number;
  
  constructor(private service:MyWalletService) { }

  ngOnInit(): void {
    if(this.transact.receiverId == parseInt(sessionStorage.getItem('accountId'))){
    this.showMsg = 1;
    this.accountId = this.transact.senderId;
    }
    else
    {
    this.showMsg=2;
    this.accountId = this.transact.receiverId;
    }
    this.service.getAccountName(this.accountId).subscribe(
      data=>{
       console.log(data)
       this.accountName=data;
      },
      error=>console.log(error)
    );
  
  }
  

}
