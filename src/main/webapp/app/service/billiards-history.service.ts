import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ApplicationConfigService } from '../core/config/application-config.service';
import { Observable } from 'rxjs';
import { createRequestOption } from '../core/request/request-util';

@Injectable({
  providedIn: 'root',
})
export class BilliardsHistoryService {
  private resourceUrl = this.applicationConfigService.getEndpointFor('api/history-billiards');

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

  changeStatus(dataModel: any): Observable<{}> {
    return this.http.post<any>(this.resourceUrl + '/change', dataModel);
  }
}