import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PHONE_REGEX, STATUS_BASE } from '../../../constant/app.constant';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { CustomerService } from '../../../service/customer.service';

@Component({
  selector: 'app-employee-form',
  templateUrl: './customer-form.component.html',
})
export class CustomerFormComponent implements OnInit {
  @ViewChild('f', { static: false }) f: NgForm;
  id;
  formModel: any = {
    name: null,
    phoneNumber: null,
    dateOfBirth: null,
  };
  statusOptions = STATUS_BASE;
  phoneRegex = PHONE_REGEX;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private toast: ToastrService,
    private translate: TranslateService,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.getModelForm();
  }

  getModelForm() {
    if (this.id) {
      this.customerService.findById(this.id).subscribe(res => {
        this.formModel = res;
      });
    }
  }

  onSubmit() {
    if (this.f.invalid) {
      return;
    }
    this.customerService.save(this.formModel).subscribe(
      () => {
        this.toast.success(this.translate.instant('common.save.success'));
        this.router.navigate(['/customer/list']);
      },
      () => {
        this.toast.error(this.translate.instant('common.save.error'));
      }
    );
  }
}
