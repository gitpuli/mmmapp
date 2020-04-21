import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserMmm } from 'app/shared/model/user-mmm.model';

type EntityResponseType = HttpResponse<IUserMmm>;
type EntityArrayResponseType = HttpResponse<IUserMmm[]>;

@Injectable({ providedIn: 'root' })
export class UserMmmService {
  public resourceUrl = SERVER_API_URL + 'api/user-mmms';

  constructor(protected http: HttpClient) {}

  create(userMmm: IUserMmm): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userMmm);
    return this.http
      .post<IUserMmm>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(userMmm: IUserMmm): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userMmm);
    return this.http
      .put<IUserMmm>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUserMmm>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUserMmm[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(userMmm: IUserMmm): IUserMmm {
    const copy: IUserMmm = Object.assign({}, userMmm, {
      createdDate: userMmm.createdDate && userMmm.createdDate.isValid() ? userMmm.createdDate.format(DATE_FORMAT) : undefined,
      lastUpdateDate: userMmm.lastUpdateDate && userMmm.lastUpdateDate.isValid() ? userMmm.lastUpdateDate.format(DATE_FORMAT) : undefined
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
      res.body.forEach((userMmm: IUserMmm) => {
        userMmm.createdDate = userMmm.createdDate ? moment(userMmm.createdDate) : undefined;
        userMmm.lastUpdateDate = userMmm.lastUpdateDate ? moment(userMmm.lastUpdateDate) : undefined;
      });
    }
    return res;
  }
}
