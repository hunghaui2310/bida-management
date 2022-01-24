import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { providerRouting } from './provider-routing.module';
import { ProviderListComponent } from './provider-list/provider-list.component';
import { ProviderFormComponent } from './provider-form/provider-form.component';

@NgModule({
  imports: [SharedModule, RouterModule.forChild(providerRouting)],
  declarations: [ProviderListComponent, ProviderFormComponent],
})
export class ProviderModule {}
