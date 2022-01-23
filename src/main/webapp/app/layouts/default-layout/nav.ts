import { INavData } from '@coreui/angular';
import { Authority } from '../../config/authority.constants';

export interface MyNavData extends INavData {
  roles?: any;
  nameI18n?: string;
}

export const navItems: MyNavData[] = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer',
    roles: [Authority.USER],
    nameI18n: 'nav.dashboard',
  },
  {
    name: 'Billiards',
    url: '/billiards/list',
    icon: 'icon-drop',
    roles: [Authority.ADMIN, Authority.USER],
    nameI18n: 'nav.billiards',
  },
  {
    name: 'Billiards History',
    url: '/billiards-history/list',
    icon: 'icon-notebook',
    roles: [Authority.ADMIN, Authority.USER],
    nameI18n: 'nav.billiards-history',
  },
  {
    name: 'Employee',
    url: '/employee',
    icon: 'icon-user-following',
    roles: [Authority.ADMIN, Authority.USER],
    nameI18n: 'nav.employee',
  },
  {
    name: 'Customer',
    url: '/customer/list',
    icon: 'icon-user-follow',
    roles: [Authority.USER],
    nameI18n: 'nav.customer',
  },
  {
    name: 'Product History',
    url: '/product-history/list',
    icon: 'icon-notebook',
    roles: [Authority.ADMIN, Authority.USER],
    nameI18n: 'nav.product-history',
  },
  {
    divider: true,
  },
  {
    name: 'Admin',
    url: '/admin',
    icon: 'icon-user',
    roles: [Authority.ADMIN, Authority.USER],
    children: [
      {
        name: 'Metrics',
        url: '/admin/metrics',
        icon: 'none',
      },
      {
        name: 'Health',
        url: '/admin/health',
        icon: 'none',
      },
      {
        name: 'Configuration',
        url: '/admin/configuration',
        icon: 'none',
      },
      {
        name: 'Logs',
        url: '/admin/logs',
        icon: 'none',
      },
      {
        name: 'API',
        url: '/admin/docs',
        icon: 'none',
      },
    ],
  },
];
