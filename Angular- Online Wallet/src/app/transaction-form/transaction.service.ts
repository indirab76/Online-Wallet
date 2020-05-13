import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Transaction } from '../my-wallet/transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseUrl = "http://localhost:9090/transaction/SearchByNameOrPhone";
  private baseUrl1 = "http://localhost:9090/transaction/allUser";
  private baseUrl2 = "http://localhost:9090/transaction/getBalance";
  private baseUrl3 = "http://localhost:9090/transaction/addTransaction";
  private baseUrl4 = "http://localhost:9090/transaction/getWalletUser";
  constructor(private http: HttpClient) { }

  searchRecieverByName(name: String, id:number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${name}/${id}`);
  }

  getAmountAvailable(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl2}/${id}`);
  }

  addTransaction(transaction: Transaction): Observable<any> {
    return this.http.post(`${this.baseUrl3}`,transaction);
  }

  getSenderDetails(id:number):Observable<any>{
    return this.http.get(`${this.baseUrl4}/${id}`);
  }
}
