import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { employeeRouting } from './customer-routing-module';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerFormComponent } from './customer-form/customer-form.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(employeeRouting)],
  declarations: [CustomerListComponent, CustomerFormComponent],
})
export class CustomerModule {}
