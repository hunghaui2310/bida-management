import { TranslateService } from '@ngx-translate/core';

export function appendParamsToUrl(query: string, regex: any, location: any) {
  const url = location.path();
  const leftRight = url.split('?');
  const left = leftRight && leftRight.length > 0 ? leftRight[0] : '';
  let right = leftRight && leftRight.length > 1 ? leftRight[1] : '';
  if (right && right.search(regex) >= 0) {
    right = right.replace(regex, query);
  } else {
    right = right.length > 0 ? right + '&' + query : query;
  }
  location.go(left, right);
}

export function isArrayNullOrEmpty(arr: any[]): boolean {
  return !arr || arr.length === 0;
}

export function deleteConfig(translate: TranslateService): any {
  return {
    title: translate.instant('common.note'),
    text: translate.instant('common.deleteConfirm'),
    confirmButtonColor: '#f86c6b',
    showConfirmButton: true,
    showCancelButton: true,
    confirmButtonText: translate.instant('common.ok'),
    cancelButtonText: translate.instant('common.cancel'),
  };
}
