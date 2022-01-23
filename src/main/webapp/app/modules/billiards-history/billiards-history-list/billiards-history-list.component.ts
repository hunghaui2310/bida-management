import { Component, OnInit } from '@angular/core';
import { ITEMS_PER_PAGE } from '../../../config/pagination.constants';
import { PAGE_REGEX, STATUS_BASE } from '../../../constant/app.constant';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { appendParamsToUrl, deleteConfig } from '../../../util/common.util';
import swal from 'sweetalert2';
import { BilliardsHistoryService } from '../../../service/billiards-history.service';

@Component({
  selector: 'app-billiards-history',
  templateUrl: './billiards-history-list.component.html',
})
export class BilliardsHistoryListComponent implements OnInit {
  modelList: any;
  pageSize = ITEMS_PER_PAGE;
  page = 1;
  totalItems = 0;
  numPages = 0;
  location: Location;
  statusOptions = STATUS_BASE;
  filter = {
    status: this.statusOptions[0].value,
    name: '',
  };

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    public route: Router,
    private toast: ToastrService,
    private translate: TranslateService,
    private billiardHistoryService: BilliardsHistoryService
  ) {}

  ngOnInit(): void {
    this.getModelList();
  }

  getModelList() {
    this.billiardHistoryService.getAll(this.filter).subscribe((res: any) => {
      if (!res || !res.body) {
        return;
      }
      this.modelList = res.body.content;
      this.totalItems = res.body.totalElements;
      this.numPages = res.body.totalPages;
    });
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
    this.route.navigate(['billiards-history', item.id]);
  }

  delete(item) {
    if (!item) {
      return;
    }
    swal.fire(deleteConfig(this.translate)).then(result => {
      if (!result || !result.value) {
        return;
      }
      this.billiardHistoryService.delete(item.id).subscribe({
        next: () => {
          this.getModelList();
          this.toast.success(this.translate.instant('common.delete.success'));
        },
        error: () => {
          this.toast.error(this.translate.instant('common.delete.success'));
        },
      });
    });
  }
}
