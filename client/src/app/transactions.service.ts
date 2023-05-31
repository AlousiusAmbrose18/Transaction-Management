import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {

  currentPage:any;

  
  //Backend api end-point
  private baseUrl = "http://localhost:8080";

  constructor(private http: HttpClient, private router: Router) {
  }

  /* Task 1: 
     -------

     This is the Api call to get Parent Transactions.
     This fuction takes the input HttpParam ( page, size and sort) and it will receive Transaction detials
     in JSON Format

  */
  getParents(params: HttpParams){
    this.currentPage = params.get('page');
    return  this.http.get<any[]>(this.baseUrl + '/parents/getParents', { params });
  }

  /* Task 2:
    --------

     This is the Api call to get  Transactions History ( Child Transactions).
     This fuction takes the input as ParentId  and it will receive Transaction detials
     in JSON Format
  */
 
  loadChildren(parentId: number) {
   return this.http.get<any[]>(this.baseUrl+`/children/${parentId}`);
  }

}
