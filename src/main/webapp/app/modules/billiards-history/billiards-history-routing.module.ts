import { Routes } from '@angular/router';
import { BilliardsHistoryListComponent } from './billiards-history-list/billiards-history-list.component';
import { BilliardsHistoryFormComponent } from './billiards-history-form/billiards-history-form.component';

export const billiardHistoryRouting: Routes = [
  {
    path: '',
    data: {
      title: 'Billiards History',
    },
    children: [
      {
        path: 'list',
        component: BilliardsHistoryListComponent,
      },
      {
        path: 'new',
        component: BilliardsHistoryFormComponent,
      },
      {
        path: ':id',
        component: BilliardsHistoryFormComponent,
      },
    ],
  },
];
