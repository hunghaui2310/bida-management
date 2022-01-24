import { Routes } from '@angular/router';
import { ProviderListComponent } from './provider-list/provider-list.component';
import { ProviderFormComponent } from './provider-form/provider-form.component';

export const providerRouting: Routes = [
  {
    path: '',
    data: {
      title: 'Provider',
    },
    children: [
      {
        path: 'list',
        component: ProviderListComponent,
      },
      {
        path: 'new',
        component: ProviderFormComponent,
      },
      {
        path: ':id',
        component: ProviderFormComponent,
      },
    ],
  },
];
