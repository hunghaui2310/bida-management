import { Component, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { ITEMS_PER_PAGE } from '../../config/pagination.constants';
import { appendParamsToUrl } from '../../util/common.util';
import { PAGE_REGEX } from '../../constant/app.constant';
import { BilliardsHistoryService } from '../../service/billiards-history.service';

@Component({
  selector: 'app-history-billiards-list',
  templateUrl: './history-billiards-list.component.html',
})
export class HistoryBilliardsListComponent implements OnInit {
  billiardsId;
  billiardsName;
  modelList;
  page = 1;
  pageSize = ITEMS_PER_PAGE;
  totalItems = 0;
  location: Location;
  numPages = 0;

  constructor(public bsModalRef: BsModalRef, private billiardsHistoryService: BilliardsHistoryService) {}

  ngOnInit(): void {
    this.getModelList();
  }

  getModelList() {
    if (this.billiardsId) {
      const filter = {
        page: this.page - 1,
        size: this.pageSize,
        billiardsId: this.billiardsId,
      };
      this.billiardsHistoryService.getAll(filter).subscribe(res => {
        console.log('res = ', res.body);
        this.modelList = res.body.content;
        this.totalItems = res.body.totalElements;
        this.numPages = res.body.totalPages;
      });
    }
  }

  pageChanged() {
    appendParamsToUrl('page=' + this.page, PAGE_REGEX, this.location);
    this.getModelList();
  }
}
