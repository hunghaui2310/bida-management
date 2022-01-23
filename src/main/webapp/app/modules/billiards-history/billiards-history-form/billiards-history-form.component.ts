import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { STATUS_BASE } from '../../../constant/app.constant';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { BilliardsHistoryService } from '../../../service/billiards-history.service';

@Component({
  selector: 'app-billiards-history-form',
  templateUrl: './billiards-history-form.component.html',
})
export class BilliardsHistoryFormComponent implements OnInit {
  @ViewChild('f', { static: false }) f: NgForm;
  id;
  formModel: any = {
    name: null,
    price: null,
    status: 1,
  };
  statusOptions = STATUS_BASE;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private toast: ToastrService,
    private translate: TranslateService,
    private billiardHistoryService: BilliardsHistoryService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.getModelForm();
  }

  getModelForm() {
    if (this.id) {
      this.billiardHistoryService.findById(this.id).subscribe(res => {
        this.formModel = res;
      });
    }
  }

  onSubmit() {
    this.billiardHistoryService.save(this.formModel).subscribe(
      () => {
        this.toast.success(this.translate.instant('common.save.success'));
        this.router.navigate(['/billiards-history/list']);
      },
      () => {
        this.toast.error(this.translate.instant('common.save.error'));
      }
    );
  }
}
