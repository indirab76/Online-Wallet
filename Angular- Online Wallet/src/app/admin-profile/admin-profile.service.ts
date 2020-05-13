import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminProfileService {
  private searchUrl = "http://localhost:9090/admin/showAdmin";
  private addUrl = "http://localhost:9090/admin/addAdmin";
  private updateUrl = "http://localhost:9090/admin/updateAdmin";
  private deleteUrl = "http://localhost:9090/admin/deleteAdmin";
  private validLoginUrl = "http://localhost:9090/admin/validLogin";
  private validateUrl = "http://localhost:9090/admin/showRegisteredUsers";
  private acceptedusersUrl = "http://localhost:9090/admin/showAcceptedUsers";
  private updateStatusUrl = "http://localhost:9090/admin/updateStatus";
  http: any;
  constructor(private httpClient: HttpClient) { }

  searchAdmin(id: any): Observable<any> {
    return this.httpClient.get(`${this.searchUrl}/${id}`)
  }
  addAdmin(product: Object): Observable<Object> {
    
    return this.httpClient.post(`${this.addUrl}/`, product);
  }

  updateAdmin(product: Object): Observable<Object> {
    return this.httpClient.put(`${this.updateUrl}/`, product);
  }

  deleteAdmin(id: any): Observable<any> {
    return this.httpClient.delete(`${this.deleteUrl}/${id}`,{responseType:'text'})
  }

  validLogin(loginName:String, password:String):Observable<any> {
    console.log("validLogin called")
   return this.httpClient.get(`${this.validLoginUrl}/${loginName}/${password}`)
  }

  showRegisteredUsers():Observable<any>{
    return this.httpClient.get(`${this.validateUrl}`)
  }

  showAcceptedUsers():Observable<any>{
    return this.httpClient.get(`${this.acceptedusersUrl}`)
  }

  updateStatus(id:any,status:String):Observable<any>{
    return this.httpClient.put(`${this.updateStatusUrl}/${id}/${status}`,null)
  }
}
