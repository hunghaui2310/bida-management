import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { productRouting } from './product-routing.module';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductFormComponent } from './product-form/product-form.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(productRouting)],
  declarations: [ProductListComponent, ProductFormComponent],
})
export class ProductModule {}
