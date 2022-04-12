import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import{ GlobalConstants } from '../config/constants';
import { Spending } from '../models/spending.model';


@Component({
  selector: 'app-spending-detail',
  templateUrl: './spending-detail.component.html',
  styleUrls: [ './spending-detail.component.css' ]
})
@Injectable()
export class SpendingDeatilComponent implements OnInit {
  displayedColumns: string[] = ['description', 'amount', 'category', 'date', 'personName', 'action'];
  spendingDetail: any;
  transactionList:any = [];
  spendingDetailTitle: any = "";
  
  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      var id = params.get('id');
      this.getSpendingDetail(id).subscribe(
        (data) => {
          this.spendingDetail = data;
          this.transactionList = this.spendingDetail.transactions;
          this.spendingDetailTitle = this.spendingDetail.title;
        }
      )
    });
  }

  getSpendingDetail(spendingId: any) : Observable<Spending> {
    return this.http.get<Spending>(GlobalConstants.spendingUrl + spendingId).pipe (
        catchError (this.handleError<Spending>('getSpending id=${spendingid}'))
    );
  }

  openDialog(action:any,obj:any) {
    return true;
  }

  updateRowData(row_obj:any){
    return true;
  }

  deleteRowData(row_obj:any){
    return true;
  }

  goBack(): void {
    this.location.back();
  }

      /**
     * Handle Http operation that failed.
     * Let the app continue.
     *
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
       private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
    
        // TODO: send the error to remote logging infrastructure
        console.error(error); // log to console instead
    
        // TODO: better job of transforming error for user consumption
        console.log(`${operation} failed: ${error.message}`);
    
        // Let the app keep running by returning an empty result.
        return of(result as T);
        };
    }
}
