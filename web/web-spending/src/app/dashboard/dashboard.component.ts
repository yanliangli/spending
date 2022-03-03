import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
    spendingList:any = [];

    constructor(private http: HttpClient) { }

    ngOnInit(): void {
        this.getSpendingList();
    }

    getSpendingList() {
         this.http.get("http://localhost:2241/api/app/spending/v1/spending").subscribe(
             data => {
                 this.spendingList = data;
             },
             errro => {
                 console.log(errro);
             }
         );        
    }
}
  