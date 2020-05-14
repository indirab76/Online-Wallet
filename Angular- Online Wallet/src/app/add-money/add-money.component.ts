import { Component, OnInit } from "@angular/core";
import { AddMoneyService } from "./add-money.service";
import { Router } from "@angular/router";
import { CardDetails } from "./add-money";

@Component({
  selector: "app-add-money",
  templateUrl: "./add-money.component.html",
  styleUrls: ["./add-money.component.css"],
})
export class AddMoneyComponent implements OnInit {
  //Session user_id
  userId = sessionStorage.getItem("userId");
  accountStatus = sessionStorage.getItem("userStatus");

  cardNo: number;
  amount: number;

  //CardNo validation
  isEmpty = true;
  isValidCard = true;
  cardStatus = false;
  isCardCheck = true;

  //........//
  //month year check
  isValidMonth = true;
  isValidYear = true;

  //Amount Validation
  isAmountEmpty = true;
  isValidAmount = true;
  isAmountProceed = false;

  //Add Money Check
  isAddDone = true;
  isNotAdd = true;

  cardsearch: CardDetails;

  carddetails_1 = true;

  support = false;

  amountStatus = false;

  ngOnInit(): void {
    this.cardsearch = new CardDetails();
  }
  constructor(private service: AddMoneyService, private router: Router) {}

  checkCard() {
    this.service.cardSearch(this.cardsearch.cardNo).subscribe(
      (data) => {
        if (data != null) {
          if (
            data.expiryMonth == this.cardsearch.expiryMonth &&
            data.expiryYear == this.cardsearch.expiryYear
          ) {
            this.cardStatus = this.isCardCheck = true;

            this.amountStatus = true;

            this.isAddDone = true;

            this.isNotAdd = true;

          }

          //........//
          if (
            data.expiryMonth != this.cardsearch.expiryMonth ||
            data.expiryYear != this.cardsearch.expiryYear
          ) {
            this.cardStatus = false;

            this.isCardCheck = false;

            this.amountStatus = false;

            this.isAddDone = true;

            this.isNotAdd = true;

          }

        }

        if (data == null) {
          console.log(data);

          this.cardStatus = false;

          this.isCardCheck = false;

          this.amountStatus = false;

          this.isAddDone = true;
          this.isNotAdd = true;

        }
      },
      (error) => {
        this.cardStatus = false;

        this.isCardCheck = false;

        this.amountStatus = false;

        this.isAddDone = true;
        this.isNotAdd = true;

        console.log(error);

        console.log(" error is comming ");
      }
    );
  }

  addMoneyToWallet() {
    this.service
      .addMoneyToWallet(
        this.userId,
        this.cardsearch.amount,
        this.cardsearch.cardNo
      )
      .subscribe(
        (data) => {

          if (data != null) {
            this.isAddDone = false;
            this.isNotAdd = true;
          }

          if (data == null) {
            this.isAddDone = true;
            this.isNotAdd = false;
            console.log(" balance is not going on ");
          }
        },

        (error) => {
          this.isAddDone = true;
          this.isNotAdd = false;
        }
      );
  }

  onSubmit() {
    this.checkCard();
  }

  onAdd() {
    this.addMoneyToWallet();
  }

  public onChangeCard(event: any): void {

    if (event == null) {
      this.isEmpty = false;
      this.isValidCard = true;
      this.cardStatus = false;
      this.amountStatus = false;
    } else if (event <= 0) {
      this.isEmpty = true;
      this.isValidCard = false;
      this.cardStatus = false;
      this.amountStatus = false;
    } else {
      this.isEmpty = true;
      this.isValidCard = true;
    }
  }

  public onChangeAmount(event: any): void {

    if (event == null) {
      this.isAmountEmpty = false;
      this.isValidAmount = true;
      this.isAmountProceed = false;
    } else if (event <= 0) {
      this.isAmountEmpty = true;
      this.isValidAmount = false;
      this.isAmountProceed = false;
    } else {
      this.isAmountEmpty = true;
      this.isValidAmount = true;
      this.isAmountProceed = true;
    }
  }

  //........//
  public onChangeMonth(event: any): void {

    if (event == null || event.length == 0) {
      this.cardStatus = false;
    } else if (event <= 0) {
      this.isValidMonth = false;
      this.cardStatus = false;
      this.amountStatus = false;
    } else {
      this.isValidMonth = true;
      this.cardStatus = true;
      this.amountStatus = false;
    }
  }

  //........//
  public onChangeYear(event: any): void {
    console.log(event);

    if (event == null || event.length == 0) {
      this.cardStatus = false;
    } else if (event <= 0) {
      this.isValidYear = false;
      this.cardStatus = false;
      this.amountStatus = false;
    } else {
      this.isValidYear = true;
      this.cardStatus = true;
      this.amountStatus = false;
    }
  }
}
