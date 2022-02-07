import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { StatisticParentComponent } from './statistic-parent/statistic-parent.component';
import { StatisticSalesComponent } from './statistic-sales/statistic-sales.component';

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild([
      {
        path: '',
        component: StatisticParentComponent,
      },
    ]),
  ],
  declarations: [StatisticParentComponent, StatisticSalesComponent],
})
export class StatisticModule {}
