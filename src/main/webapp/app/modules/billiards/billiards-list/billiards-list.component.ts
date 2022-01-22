import { Component, OnInit } from '@angular/core';
import { ITEMS_PER_PAGE } from '../../../config/pagination.constants';
import { ActivatedRoute, Router } from '@angular/router';
import { BilliardsService } from '../../../service/billiards.service';
import { appendParamsToUrl, deleteConfig } from '../../../util/common.util';
import { PAGE_REGEX, STATUS_BASE } from '../../../constant/app.constant';
import swal from 'sweetalert2';
import { TranslateService } from '@ngx-translate/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-billiards',
  templateUrl: './billiards-list.component.html',
})
export class BilliardsListComponent implements OnInit {
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
    private billiardService: BilliardsService
  ) {}

  ngOnInit(): void {
    this.getModelList();
  }

  getModelList() {
    this.billiardService.getAll(this.filter).subscribe((res: any) => {
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
    this.route.navigate(['billiards', item.id]);
  }

  delete(item) {
    if (!item) {
      return;
    }
    swal.fire(deleteConfig(this.translate)).then(result => {
      if (!result || !result.value) {
        return;
      }
      this.billiardService.delete(item.id).subscribe({
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
