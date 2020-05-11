import { Component, OnInit } from '@angular/core';
import { Transaction } from '../my-wallet/transaction';
import { TransactionService } from './transaction.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transaction-form',
  templateUrl: './transaction-form.component.html',
  styleUrls: ['./transaction-form.component.css']
})
export class TransactionFormComponent implements OnInit {
  accountStatus=sessionStorage.getItem('userStatus');
  userid ;  
  recieverDetails;
  userDetails;
  recieverList;
  search: String;
  isRecieverValid = false;
  isRecieverFound = true;
  amountAvailable: number;
  rtransaction;

  transaction = new Transaction();
  amountNeed: number;
  isBalance = true;
  isNegative = false;
  isEmpty = true;
  isInvalid = true;

  constructor(private service: TransactionService, private router: Router) {
    this.userid = sessionStorage.getItem('userId')   ////replace 1 with sessionstorage.getItem()
    this.service.getSenderDetails(this.userid).subscribe(res => { this.userDetails = res },
      error => console.log(error)
    );
    

    this.service.getAmountAvailable(this.userid).subscribe(res => this.amountAvailable = res);

  }
  ngOnInit(): void {
   
  }

  checkStatus(){
    console.log(this.userDetails.walletAccount.status)
    if (this.userDetails.walletAccount.status == "Not Approve") {
      alert("Hey " + this.userDetails.userName + " ,Wait for the admin approval");
      this.router.navigate(['home']);
    } else if (this.userDetails.walletAccount.status == "Reject") {
      alert("Hey " + this.userDetails.userName + " ,Admin has rejected the account please create again with correct details!!");
      this.router.navigate(['register']);
    }
  }

  setClickedRow(event: any): void {
    this.recieverDetails = event;
    this.checkStatus();  //call this line if status is working
  }

  onSubmit(): void {
    this.transaction.dateOfTransaction = new Date();
    this.transaction.receiverId = this.recieverDetails.walletAccount.accountId;
    this.transaction.senderId = this.userDetails.walletAccount.accountId;
    console.log(this.transaction);
    this.rtransaction = new Transaction();
    this.service.addTransaction(this.transaction).subscribe(res => {
      this.rtransaction = res;
      //console.log(this.rtransaction.dateOfTransaction)
    },
      error => console.log(error)
    );
    alert("Transaction is under processing.......");
  }

  showSubmit(): void {
    if (this.isEmpty == false && this.isBalance == true && this.isNegative == false && this.isRecieverValid == true)
      this.isInvalid = false;
    else
      this.isInvalid = true;

  }

  public onChange(event: String): void {
    if (event.length == 0) {
      this.isRecieverValid = false;
      this.recieverList = null;
      this.isRecieverFound = true;
    }
    else {
      this.service.searchRecieverByName(event, this.userid).subscribe(res => {
        this.recieverList = res;
        if (res == "") {
          this.isRecieverFound = false;
          this.isRecieverValid = false;
        }
        else {
          this.isRecieverValid = true;
          this.isRecieverFound = true;
        }
      },
        error => console.log(error)
      );
    }
  }

  public onChangeBalance(event: any): void {
    if (event == null) {
      this.isEmpty = true;
      this.isBalance = true;
      this.isNegative = false;
    }
    else {
      if (event > this.amountAvailable) {
        this.isBalance = false;
        this.amountNeed = event - this.amountAvailable;
      }
      else
        this.isBalance = true;

      if (event <= 0)
        this.isNegative = true;
      else
        this.isNegative = false;

      this.isEmpty = false;
    }
    this.showSubmit();
  }

  addMoney() {
   // this.transaction = new Transaction();
    this.router.navigate(['addMoney']);
  }

}
