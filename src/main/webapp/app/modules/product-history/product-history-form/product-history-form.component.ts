import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { STATUS_BASE } from '../../../constant/app.constant';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { ProductHistoryService } from '../../../service/product-history.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-history-form.component.html',
})
export class ProductHistoryFormComponent implements OnInit {
  @ViewChild('f', { static: false }) f: NgForm;
  id;
  formModel: any = {
    name: null,
    price: null,
    status: 1,
  };
  statusOptions = STATUS_BASE;
  providers = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private toast: ToastrService,
    private translate: TranslateService,
    private productHistoryService: ProductHistoryService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.getModelForm();
  }

  getModelForm() {
    if (this.id) {
      this.productHistoryService.findById(this.id).subscribe(res => {
        this.formModel = res;
      });
    }
  }

  onSubmit() {
    if (this.f.invalid) {
      return;
    }
    this.productHistoryService.save(this.formModel).subscribe(
      () => {
        this.toast.success(this.translate.instant('common.save.success'));
        this.router.navigate(['/history-product/list']);
      },
      () => {
        this.toast.error(this.translate.instant('common.save.error'));
      }
    );
  }
}
