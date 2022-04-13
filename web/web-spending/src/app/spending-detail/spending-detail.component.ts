import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import{ GlobalConstants } from '../config/constants';
import { Spending } from '../models/spending.model';
import { HttpErrorHandlerService } from '../shared/http-error-handler';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import {MatSort, Sort} from '@angular/material/sort';

@Component({
  selector: 'app-spending-detail',
  templateUrl: './spending-detail.component.html',
  styleUrls: [ './spending-detail.component.css' ]
})
export class SpendingDeatilComponent implements OnInit {
  displayedColumns: string[] = ['description', 'amount', 'category', 'date', 'personName', 'action'];
  spendingDetail: any;
  spendingDetailTitle: any = "";
  dataSource: any;
  private paginator: MatPaginator | undefined;
  private sort: MatSort | undefined;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private http: HttpClient,
    private httpErrorHandlerService: HttpErrorHandlerService,
    private _liveAnnouncer: LiveAnnouncer
  ) {}

  @ViewChild(MatSort) set matSort(ms: MatSort) {
    this.sort = ms;
  }

  @ViewChild(MatPaginator) set matPaginator(mp: MatPaginator) {
    this.paginator = mp;
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      var id = params.get('id');
      this.getSpendingDetail(id).subscribe(
        (data) => {
          this.spendingDetail = data;
          this.dataSource = new MatTableDataSource<Spending>(data.transactions);
          this.spendingDetailTitle = this.spendingDetail.title;
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
          if (this.paginator && this.sort) {
            this.applyFilter('');
          }
        }
      )
    });
  }

  getSpendingDetail(spendingId: any) : Observable<Spending> {
    return this.http.get<Spending>(GlobalConstants.spendingUrl + spendingId).pipe (
        catchError (this.httpErrorHandlerService.handleError<Spending>('getSpending id=${spendingid}'))
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

  goBack(): void {
    this.location.back();
  }
}
