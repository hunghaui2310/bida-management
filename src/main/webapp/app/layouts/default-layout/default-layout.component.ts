import { Component } from '@angular/core';
import { navItems } from './nav';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html',
})
export class DefaultLayoutComponent {
  public sidebarMinimized = false;
  public navItems = navItems;

  toggleMinimize(e: any) {
    this.sidebarMinimized = e;
  }
}
