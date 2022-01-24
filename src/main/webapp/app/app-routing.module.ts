import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { errorRoute } from './layouts/error/error.route';
import { navbarRoute } from './layouts/navbar/navbar.route';
import { Authority } from 'app/config/authority.constants';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DefaultLayoutComponent } from './layouts/default-layout/default-layout.component';
import { DashboardComponent } from './layouts/dashboard/dashboard.component';

const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];

@NgModule({
  imports: [
    RouterModule.forRoot([
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
      {
        path: '',
        component: DefaultLayoutComponent,
        data: {
          title: 'Home',
        },
        children: [
          {
            path: 'dashboard',
            data: {
              authorities: [Authority.USER],
            },
            canActivate: [UserRouteAccessService],
            component: DashboardComponent,
          },
          {
            path: 'admin',
            data: {
              authorities: [Authority.ADMIN],
            },
            canActivate: [UserRouteAccessService],
            loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule),
          },
          {
            path: 'account',
            loadChildren: () => import('./account/account.module').then(m => m.AccountModule),
          },
          {
            path: 'billiards',
            data: {
              authorities: [Authority.ADMIN],
            },
            canActivate: [UserRouteAccessService],
            loadChildren: () => import('./modules/billiards/billiards.module').then(m => m.BilliardsModule),
          },
          {
            path: 'billiards-history',
            data: {
              authorities: [Authority.ADMIN],
            },
            canActivate: [UserRouteAccessService],
            loadChildren: () => import('./modules/billiards-history/billiards-history.module').then(m => m.BilliardsHistoryModule),
          },
          {
            path: 'employee',
            data: {
              authorities: [Authority.ADMIN],
            },
            canActivate: [UserRouteAccessService],
            loadChildren: () => import('./modules/employee/user-management.module').then(m => m.UserManagementModule),
          },
          {
            path: 'customer',
            data: {
              authorities: [Authority.ADMIN],
            },
            canActivate: [UserRouteAccessService],
            loadChildren: () => import('./modules/customer/customer.module').then(m => m.CustomerModule),
          },
          {
            path: 'product',
            loadChildren: () => import('./modules/product/product.module').then(m => m.ProductModule),
          },
          {
            path: 'product-history',
            loadChildren: () => import('./modules/product-history/product-history.module').then(m => m.ProductHistoryModule),
          },
          {
            path: 'provider',
            loadChildren: () => import('./modules/provider/provider.module').then(m => m.ProviderModule),
          },
        ],
      },
      {
        path: 'login',
        loadChildren: () => import('./login/login.module').then(m => m.LoginModule),
      },
      ...LAYOUT_ROUTES,
    ]),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
