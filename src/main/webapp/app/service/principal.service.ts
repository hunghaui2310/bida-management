import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ApplicationConfigService } from '../core/config/application-config.service';

@Injectable({
  providedIn: 'root',
})
export class PrincipalService {
  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getCurrentUser(): Observable<HttpResponse<any>> {
    return this.http.get<any>(this.applicationConfigService.getEndpointFor('api/account'));
  }
}
