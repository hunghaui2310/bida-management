import { BilliardsListComponent } from './billiards-list/billiards-list.component';
import { BilliardsFormComponent } from './billiards-form/billiards-form.component';
import { Routes } from '@angular/router';

export const billiardsRouting: Routes = [
  {
    path: '',
    data: {
      title: 'Billiards',
    },
    children: [
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
    ],
  },
];
