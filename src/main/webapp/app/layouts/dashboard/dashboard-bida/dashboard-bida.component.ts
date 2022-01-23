import { Component, OnInit } from '@angular/core';
import { BilliardsService } from '../../../service/billiards.service';
import swal from 'sweetalert2';
import { deleteConfig } from '../../../util/common.util';
import { TranslateService } from '@ngx-translate/core';
import { BilliardsHistoryService } from '../../../service/billiards-history.service';
import { ToastrService } from 'ngx-toastr';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { HistoryBilliardsListComponent } from '../../../shared/history-billiards-list/history-billiards-list.component';

@Component({
  selector: 'app-dashboard-bida',
  templateUrl: './dashboard-bida.component.html',
})
export class DashboardBidaComponent implements OnInit {
  dashboardBida: any;
  bsModalRef: BsModalRef;

  constructor(
    private billiardsService: BilliardsService,
    private billiardsHistoryService: BilliardsHistoryService,
    private toast: ToastrService,
    private modalService: BsModalService,
    private translate: TranslateService
  ) {}

  ngOnInit(): void {
    this.getAllBilliards();
  }

  getAllBilliards() {
    this.billiardsService.getAll().subscribe(res => {
      if (!res || !res.body) {
        return;
      }
      this.dashboardBida = res.body;
    });
  }

  switch(item) {
    swal.fire(deleteConfig(this.translate, 'common.changeStateConfirm')).then(result => {
      if (!result || !result.value) {
        return;
      }
      const billiardsHistory = {
        billiards: item,
        id: null,
      } as any;
      if (item.state) {
        billiardsHistory.id = item.historyBilliardLast.id;
        billiardsHistory.startTimeActive = item.historyBilliardLast.startTimeActive;
      }
      this.billiardsHistoryService.changeStatus(billiardsHistory).subscribe(
        () => {
          this.toast.success(this.translate.instant('common.change.success'));
          this.getAllBilliards();
        },
        () => {
          this.toast.error(this.translate.instant('common.change.error'));
        }
      );
    });
  }

  addService() {
    console.log('called to add service');
  }

  printBill() {
    console.log('called to print bill');
  }

  seeHistory(item) {
    const initialState = {
      billiardsId: item.id,
      billiardsName: item.name,
    };
    this.bsModalRef = this.modalService.show(HistoryBilliardsListComponent, { initialState });
    this.bsModalRef.setClass('modal-xl');
  }
}
