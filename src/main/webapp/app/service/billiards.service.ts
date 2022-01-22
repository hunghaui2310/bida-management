import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { createRequestOption } from '../core/request/request-util';
import { ApplicationConfigService } from '../core/config/application-config.service';

@Injectable({
  providedIn: 'root',
})
export class BilliardsService {
  private resourceUrl = this.applicationConfigService.getEndpointFor('api/billiards');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getAll(req?): Observable<HttpResponse<any>> {
    const options = createRequestOption(req);
    return this.http.get<any>(this.resourceUrl, { params: options, observe: 'response' });
  }

  save(dataModel: any): Observable<{}> {
    return this.http.post<any>(this.resourceUrl + '/save', dataModel);
  }

  findById(id): Observable<HttpResponse<any>> {
    return this.http.get<any>(`${this.resourceUrl}/${id}`);
  }

  delete(id: number): Observable<{}> {
    return this.http.delete(`${this.resourceUrl}/${id}`);
  }
}
