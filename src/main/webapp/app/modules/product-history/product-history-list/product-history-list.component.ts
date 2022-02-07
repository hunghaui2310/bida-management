import { Component, OnInit } from '@angular/core';
import { ITEMS_PER_PAGE } from '../../../config/pagination.constants';
import { PAGE_REGEX, STATUS_BASE } from '../../../constant/app.constant';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { appendParamsToUrl, deleteConfig } from '../../../util/common.util';
import swal from 'sweetalert2';
import { ProductHistoryService } from '../../../service/product-history.service';
import { concat, Observable, of, Subject } from 'rxjs';
import { catchError, distinctUntilChanged, map, startWith, switchMap, tap } from 'rxjs/operators';
import { ProviderService } from '../../../service/provider.service';
import { UserManagementService } from '../../employee/service/user-management.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-history-list.component.html',
})
export class ProductHistoryListComponent implements OnInit {
  modelList: any;
  pageSize = ITEMS_PER_PAGE;
  page = 1;
  totalItems = 0;
  numPages = 0;
  location: Location;
  statusOptions = STATUS_BASE;
  filter = {
    providerId: null,
    productName: '',
    employeeId: null,
  };
  providers$: Observable<any>;
  loadingProvider = false;
  providerInput$ = new Subject<string>();
  employees$: Observable<any>;
  loadingEmployee = false;
  employeeInput$ = new Subject<string>();

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    public route: Router,
    private toast: ToastrService,
    private translate: TranslateService,
    private providerService: ProviderService,
    private productHistoryService: ProductHistoryService,
    private employeeService: UserManagementService
  ) {}

  ngOnInit(): void {
    this.getModelList();
    this.loadGroup();
    this.loadEmployee();
  }

  getModelList() {
    const param = { page: this.page - 1, size: this.pageSize };
    this.productHistoryService.search(this.filter, param).subscribe((res: any) => {
      if (!res || !res.body) {
        return;
      }
      this.modelList = res.body.content;
      this.totalItems = res.body.totalElements;
      this.numPages = res.body.totalPages;
    });
  }

  loadGroup() {
    this.providers$ = concat(
      of([]),
      this.providerInput$.pipe(
        startWith(''),
        distinctUntilChanged(),
        tap(() => (this.loadingProvider = true)),
        switchMap(term =>
          this.providerService.getAll({ name: term }).pipe(
            map(res => res.body),
            catchError(() => of([])),
            tap(() => (this.loadingProvider = false))
          )
        )
      )
    );
  }

  loadEmployee() {
    this.employees$ = concat(
      of([]),
      this.employeeInput$.pipe(
        startWith(''),
        distinctUntilChanged(),
        tap(() => (this.loadingEmployee = true)),
        switchMap(term =>
          this.employeeService.getAll({ login: term }).pipe(
            map(res => res.body),
            catchError(() => of([])),
            tap(() => (this.loadingEmployee = false))
          )
        )
      )
    );
  }

  pageChanged() {
    appendParamsToUrl('page=' + this.page, PAGE_REGEX, this.location);
    this.getModelList();
  }

  search() {
    this.page = 1;
    this.getModelList();
  }

  edit(item) {
    if (!item) {
      return;
    }
    this.route.navigate(['product-history', item.id]);
  }

  delete(item) {
    if (!item) {
      return;
    }
    swal.fire(deleteConfig(this.translate)).then(result => {
      if (!result || !result.value) {
        return;
      }
      this.productHistoryService.delete(item.id).subscribe({
        next: () => {
          this.getModelList();
          this.toast.success(this.translate.instant('common.delete.success'));
        },
        error: () => {
          this.toast.error(this.translate.instant('common.delete.error'));
        },
      });
    });
  }
}
