import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Child } from '../child';
import { TransactionsService } from '../transactions.service';

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.css']
})
export class ChildComponent {

  constructor(private route: ActivatedRoute, private service : TransactionsService, private router : Router) {
  }
  
  children : Child[] = [];

  parentId:any;

  private baseUrl = "http://localhost:8080";

  ngOnInit() {
    this.parentId = this.route.snapshot.params['parentId'];
    this.loadChildern();
  }  

  /*
   This is the Api call to get  Transactions History ( Child Transactions).
     This fuction takes the input as ParentId  and it will receive Transaction detials
     in JSON Format
  */

  loadChildern(){
    this.service.loadChildren(this.parentId).subscribe(data => {
      this.children = Object.values(data);
    });
  }

}
