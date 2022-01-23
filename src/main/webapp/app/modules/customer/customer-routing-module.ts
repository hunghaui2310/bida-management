import { Routes } from '@angular/router';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerFormComponent } from './customer-form/customer-form.component';

export const employeeRouting: Routes = [
  {
    path: '',
    data: {
      title: 'Billiards History',
    },
    children: [
      {
        path: 'list',
        component: CustomerListComponent,
      },
      {
        path: 'new',
        component: CustomerFormComponent,
      },
      {
        path: ':id',
        component: CustomerFormComponent,
      },
    ],
  },
];
