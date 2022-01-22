import { NgModule } from '@angular/core';
import { BilliardsListComponent } from './billiards-list/billiards-list.component';
import { SharedModule } from '../../shared/shared.module';
import { billiardsRouting } from './billiards-routing.module';
import { RouterModule } from '@angular/router';
import { BilliardsFormComponent } from './billiards-form/billiards-form.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(billiardsRouting)],
  declarations: [BilliardsListComponent, BilliardsFormComponent],
})
export class BilliardsModule {}
