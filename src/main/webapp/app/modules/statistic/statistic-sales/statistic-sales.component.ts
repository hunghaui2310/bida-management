import { Component } from '@angular/core';
import { CYCLES } from '../../../constant/app.constant';

@Component({
  selector: 'app-statistic-sales',
  templateUrl: './statistic-sales.component.html',
})
export class StatisticSalesComponent {
  cycles = CYCLES;
  cycle = this.cycles[0].value;
}
