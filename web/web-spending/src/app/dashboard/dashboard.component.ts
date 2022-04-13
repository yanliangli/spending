import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable} from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Spending } from '../models/spending.model';
import{ GlobalConstants } from '../config/constants';
import { HttpErrorHandlerService } from '../shared/http-error-handler';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { LiveAnnouncer } from '@angular/cdk/a11y';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
    displayedColumns: string[] = ['title', 'createDate', 'action'];
    dataSource: any;
    private paginator: MatPaginator | undefined;
    private sort: MatSort | undefined;
  
    constructor(private http: HttpClient,
        private router: Router, 
        private httpErrorHandlerService: HttpErrorHandlerService,
        private _liveAnnouncer: LiveAnnouncer
    ) { }

    ngOnInit(): void {
        this.getSpendingList().subscribe(
            (data) => {
                this.dataSource = data;
            }
        );
    }

    getSpendingList() : Observable<Spending[]> {
         return this.http.get<Spending[]>(GlobalConstants.spendingUrl).pipe (
             catchError (this.httpErrorHandlerService.handleError<Spending[]>('getSpendingList', []))
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
    
    
    
    applyFilter(filterValue: string) {
        filterValue = filterValue.trim(); // Remove whitespace
        filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
        this.dataSource.filter = filterValue;
    } 

    sortData(sortState: Sort) {
        if (sortState.direction) {
        this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
        } else {
        this._liveAnnouncer.announce('Sorting cleared');
        }
    }

}
  