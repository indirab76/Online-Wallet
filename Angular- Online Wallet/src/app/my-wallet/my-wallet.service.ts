import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyWalletService {

  private balanceUrl = "http://localhost:9090/wallet/showBalance";
  private transactsUrl = "http://localhost:9090/wallet/showTransactions";
   private nameUrl = "http://localhost:9090/wallet//getAccountName";
  constructor(private httpClient: HttpClient) { }

  showBalance(id: any): Observable<any> {
    return this.httpClient.get(`${this.balanceUrl}/${id}`)
  }

  showTransactions(id:any):Observable<any>{
    return this.httpClient.get(`${this.transactsUrl}/${id}`)
  }
  getAccountName(id:any):Observable<any>{
    return this.httpClient.get(`${this.nameUrl}/${id}`)
  }
}
