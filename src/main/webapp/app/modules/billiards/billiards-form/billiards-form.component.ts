import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BilliardsService } from '../../../service/billiards.service';
import { STATUS_BASE } from '../../../constant/app.constant';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-billiards-form',
  templateUrl: './billiards-form.component.html',
})
export class BilliardsFormComponent implements OnInit {
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
    private billiardService: BilliardsService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.getModelForm();
  }

  getModelForm() {
    if (this.id) {
      this.billiardService.findById(this.id).subscribe(res => {
        this.formModel = res;
      });
    }
  }

  onSubmit() {
    this.billiardService.save(this.formModel).subscribe(
      () => {
        this.toast.success(this.translate.instant('common.save.success'));
        this.router.navigate(['/billiards/list']);
      },
      () => {
        this.toast.error(this.translate.instant('common.save.error'));
      }
    );
  }
}
