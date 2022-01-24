import { NgModule } from '@angular/core';

import { SharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { TranslateDirective } from './language/translate.directive';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { DurationPipe } from './date/duration.pipe';
import { FormatMediumDatetimePipe } from './date/format-medium-datetime.pipe';
import { FormatMediumDatePipe } from './date/format-medium-date.pipe';
import { SortByDirective } from './sort/sort-by.directive';
import { SortDirective } from './sort/sort.directive';
import { ItemCountComponent } from './pagination/item-count.component';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { StatusPipe } from './pipe/status.pipe';
import { NgSelectModule } from '@ng-select/ng-select';
import { NgxTrimDirectiveModule } from 'ngx-trim-directive';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { GlobalConfig, ToastrModule } from 'ngx-toastr';
import { HistoryBilliardsListComponent } from './history-billiards-list/history-billiards-list.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { LmToPipe } from './pipe/lmTo.pipe';

@NgModule({
  imports: [
    SharedLibsModule,
    PaginationModule.forRoot(),
    NgSelectModule,
    NgxTrimDirectiveModule,
    SweetAlert2Module,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-bottom-right',
      preventDuplicates: false,
      newestOnTop: true,
      maxOpened: 1,
    } as Partial<GlobalConfig>),
    ModalModule.forRoot(),
  ],
  declarations: [
    FindLanguageFromKeyPipe,
    TranslateDirective,
    AlertComponent,
    AlertErrorComponent,
    HasAnyAuthorityDirective,
    DurationPipe,
    FormatMediumDatetimePipe,
    FormatMediumDatePipe,
    SortByDirective,
    SortDirective,
    ItemCountComponent,
    StatusPipe,
    HistoryBilliardsListComponent,
    LmToPipe,
  ],
  exports: [
    SharedLibsModule,
    PaginationModule,
    NgSelectModule,
    SweetAlert2Module,
    ToastrModule,
    ModalModule,
    NgxTrimDirectiveModule,
    FindLanguageFromKeyPipe,
    TranslateDirective,
    AlertComponent,
    AlertErrorComponent,
    HasAnyAuthorityDirective,
    DurationPipe,
    FormatMediumDatetimePipe,
    FormatMediumDatePipe,
    SortByDirective,
    SortDirective,
    ItemCountComponent,
    StatusPipe,
    HistoryBilliardsListComponent,
    LmToPipe,
  ],
})
export class SharedModule {}
