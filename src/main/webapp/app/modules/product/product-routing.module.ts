import { Routes } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductFormComponent } from './product-form/product-form.component';

export const productRouting: Routes = [
  {
    path: '',
    data: {
      title: 'Product',
    },
    children: [
      {
        path: 'list',
        component: ProductListComponent,
      },
      {
        path: 'new',
        component: ProductFormComponent,
      },
      {
        path: ':id',
        component: ProductFormComponent,
      },
    ],
  },
];
