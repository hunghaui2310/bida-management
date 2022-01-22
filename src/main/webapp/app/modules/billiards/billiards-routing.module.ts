import { BilliardsListComponent } from './billiards-list/billiards-list.component';
import { BilliardsFormComponent } from './billiards-form/billiards-form.component';

export const billiardsRouting = [
  {
    path: 'list',
    component: BilliardsListComponent,
  },
  {
    path: 'new',
    component: BilliardsFormComponent,
  },
  {
    path: ':id',
    component: BilliardsFormComponent,
  },
];
