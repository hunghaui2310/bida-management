import { Routes } from '@angular/router';
import { ProductHistoryListComponent } from './product-history-list/product-history-list.component';
import { ProductHistoryFormComponent } from './product-history-form/product-history-form.component';

export const productHistoryRouting: Routes = [
  {
    path: '',
    data: {
      title: 'Product',
    },
    children: [
      {
        path: 'list',
        component: ProductHistoryListComponent,
      },
      {
        path: 'new',
        component: ProductHistoryFormComponent,
      },
      {
        path: ':id',
        component: ProductHistoryFormComponent,
      },
    ],
  },
];
