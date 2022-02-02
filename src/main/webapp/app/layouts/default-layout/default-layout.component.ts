import { Component, OnInit } from '@angular/core';
import { navItems } from './nav';
import { LANGUAGES } from '../../config/language.constants';
import { SessionStorageService } from 'ngx-webstorage';
import { TranslateService } from '@ngx-translate/core';
import { Account } from '../../core/auth/account.model';
import { Router } from '@angular/router';
import { LoginService } from '../../login/login.service';
import { PrincipalService } from '../../service/principal.service';
import _ from 'lodash';
import { Authority } from '../../config/authority.constants';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
})
export class DefaultLayoutComponent implements OnInit {
  public sidebarMinimized = false;
  public navItems = [];
  languages = LANGUAGES;
  isNavbarCollapsed = true;
  account: Account | null = null;

  constructor(
    private sessionStorageService: SessionStorageService,
    private translateService: TranslateService,
    private loginService: LoginService,
    private principal: PrincipalService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCurrentUser();
  }

  toggleMinimize(e: any) {
    this.sidebarMinimized = e;
  }

  changeLanguage(languageKey: string): void {
    this.sessionStorageService.store('locale', languageKey);
    this.translateService.use(languageKey);
  }

  collapseNavbar(): void {
    this.isNavbarCollapsed = true;
  }

  toggleNavbar(): void {
    this.isNavbarCollapsed = !this.isNavbarCollapsed;
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  logout(): void {
    this.collapseNavbar();
    this.loginService.logout();
    this.router.navigate(['']);
  }

  getCurrentUser() {
    this.principal.getCurrentUser().subscribe((res: any) => {
      this.account = res;
      this.buildSideBarByRole(res.authorities);
    });
  }

  isAdmin(roles: []): boolean {
    if (!roles) {
      return false;
    }
    return roles.find(item => item == Authority.ADMIN);
  }

  buildSideBarByRole(roles: []) {
    this.navItems = [];
    navItems.forEach(item => {
      if (!item || !item.roles) {
        return;
      }
      if (_.isEqual(_.sortBy(roles), _.sortBy(item.roles)) || this.isAdmin(roles)) {
        item.name = item.nameI18n ? this.translateService.instant(item.nameI18n) : item.name;
        this.navItems.push(item);
      }
    });
  }
}
