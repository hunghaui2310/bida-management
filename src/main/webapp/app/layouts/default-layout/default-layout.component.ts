import { Component } from '@angular/core';
import { navItems } from './nav';
import { LANGUAGES } from '../../config/language.constants';
import { SessionStorageService } from 'ngx-webstorage';
import { TranslateService } from '@ngx-translate/core';
import { Account } from '../../core/auth/account.model';
import { Router } from '@angular/router';
import { LoginService } from '../../login/login.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
})
export class DefaultLayoutComponent {
  public sidebarMinimized = false;
  public navItems = navItems;
  languages = LANGUAGES;
  isNavbarCollapsed = true;
  account: Account | null = null;

  constructor(
    private sessionStorageService: SessionStorageService,
    private translateService: TranslateService,
    private loginService: LoginService,
    private router: Router
  ) {}

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
}
