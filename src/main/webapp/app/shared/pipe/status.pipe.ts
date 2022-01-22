import { Pipe, PipeTransform } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Pipe({
  name: 'status',
})
export class StatusPipe implements PipeTransform {
  constructor(private translate: TranslateService) {}

  transform(value: any): any {
    if (value === 1) {
      return '<span class="badge badge-success">' + this.translate.instant('status.active') + '</span>';
    } else if (value == 0) {
      return '<span class="badge badge-secondary">' + this.translate.instant('status.nonactive') + '</span>';
    }
    return value;
  }
}
