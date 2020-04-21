import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserInstance } from 'app/shared/model/user-instance.model';

type EntityResponseType = HttpResponse<IUserInstance>;
type EntityArrayResponseType = HttpResponse<IUserInstance[]>;

@Injectable({ providedIn: 'root' })
export class UserInstanceService {
  public resourceUrl = SERVER_API_URL + 'api/user-instances';

  constructor(protected http: HttpClient) {}

  create(userInstance: IUserInstance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userInstance);
    return this.http
      .post<IUserInstance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(userInstance: IUserInstance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userInstance);
    return this.http
      .put<IUserInstance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUserInstance>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUserInstance[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(userInstance: IUserInstance): IUserInstance {
    const copy: IUserInstance = Object.assign({}, userInstance, {
      createdDate:
        userInstance.createdDate && userInstance.createdDate.isValid() ? userInstance.createdDate.format(DATE_FORMAT) : undefined,
      lastUpdateDate:
        userInstance.lastUpdateDate && userInstance.lastUpdateDate.isValid() ? userInstance.lastUpdateDate.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastUpdateDate = res.body.lastUpdateDate ? moment(res.body.lastUpdateDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((userInstance: IUserInstance) => {
        userInstance.createdDate = userInstance.createdDate ? moment(userInstance.createdDate) : undefined;
        userInstance.lastUpdateDate = userInstance.lastUpdateDate ? moment(userInstance.lastUpdateDate) : undefined;
      });
    }
    return res;
  }
}
