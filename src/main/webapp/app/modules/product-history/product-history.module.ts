import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { ProductHistoryListComponent } from './product-history-list/product-history-list.component';
import { productHistoryRouting } from './product-history-routing.module';
import { ProductHistoryFormComponent } from './product-history-form/product-history-form.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(productHistoryRouting)],
  declarations: [ProductHistoryListComponent, ProductHistoryFormComponent],
})
export class ProductHistoryModule {}
