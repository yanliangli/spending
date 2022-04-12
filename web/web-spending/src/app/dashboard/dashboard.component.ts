import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Spending } from '../models/spending.model';
import{ GlobalConstants } from '../config/constants';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
@Injectable()
export class DashboardComponent implements OnInit {
    displayedColumns: string[] = ['title', 'createDate', 'action'];
    spendingList:any = [];

    constructor(private http: HttpClient, private router: Router) { }

    ngOnInit(): void {
        this.spendingList = this.getSpendingList();
    }

    getSpendingList() : Observable<Spending[]> {
         return this.http.get<Spending[]>(GlobalConstants.spendingUrl).pipe (
             catchError (this.handleError<Spending[]>('getSpendingList', []))
         );
    }

    navigateTo(id: string) {
        this.router.navigate(['/detail/',  id]);
    }

    openAddDialog(action:any) {
        return true;
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
  