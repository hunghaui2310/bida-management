import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { billiardHistoryRouting } from './billiards-history-routing.module';
import { BilliardsHistoryListComponent } from './billiards-history-list/billiards-history-list.component';
import { BilliardsHistoryFormComponent } from './billiards-history-form/billiards-history-form.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(billiardHistoryRouting)],
  declarations: [BilliardsHistoryListComponent, BilliardsHistoryFormComponent],
})
export class BilliardsHistoryModule {}
