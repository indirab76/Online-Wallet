import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CardDetails } from './add-money';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AddMoneyService {
  private baseUrl_1 = "http://localhost:9090/wallet/showAll";

  private baseUrl_2 = "http://localhost:9090/wallet/addMoney";
  
  private baseUrl = "http://localhost:9090/wallet/cardDetails";


  constructor(private http: HttpClient) { 

  }


  cardSearch(cardNo: Number): Observable<CardDetails> {
console.log("card is searching")
    return this.http.get<CardDetails>(`${this.baseUrl}/${cardNo}`);
  }


  getCardDetails_1(): Observable<any> {

    return this.http.get(`${this.baseUrl_1}`);
  }

  addMoneyToWallet(userId :any , amount:Number , cardNo:Number): Observable<any> {

    console.log("adding money to wallet")
  // return this.http.put(`${this.baseUrl_2}/${userId}/${amountenter}/${cardNo}`)

   return this.http.put(`${this.baseUrl_2}/${userId}/${amount}/${cardNo}`,{responseType: 'any'});

  }
}
