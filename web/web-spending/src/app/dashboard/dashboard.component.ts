import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatTable} from '@angular/material/table';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
    displayedColumns: string[] = ['title'];
    spendingList:any = [];

    constructor(private http: HttpClient, private router: Router) { }

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

    navigateTo(id: string) {
        this.router.navigate(['/detail/',  id]);
    }
}
  