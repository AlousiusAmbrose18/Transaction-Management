import { JsonPipe } from '@angular/common';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Parent } from '../parent';
import { TransactionsService } from '../transactions.service';

@Component({
  selector: 'app-parent',
  templateUrl: './parent.component.html',
  styleUrls: ['./parent.component.css']
})
export class ParentComponent {

  parents: Parent[] = [];
  currentPage: any;
  totalPages: any;


  constructor(private service: TransactionsService) {
    this.currentPage = 0;
    this.totalPages = 0;
  }

  ngOnInit() {
    this.getParents();
  }

  /* This is the Api call to get Parent Transactions
     This fuction takes the input HttpParam ( page, size and sort) and it will receive Transaction detials
     in JSON Format
  */

  getParents() {
    const params = new HttpParams()
      .set('page', this.currentPage)
      .set('pageSize', '2')
      .set('sortBy', 'id');

      this.service.getParents(params).subscribe(data => {
      this.parents = Object.values(data)[0];
      this.totalPages = Object.values(data)[1];
    });;
  }

  
  prevPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getParents();
    }

  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.getParents();
    }
  }




}
