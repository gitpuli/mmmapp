import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserGoalMmm } from 'app/shared/model/user-goal-mmm.model';

type EntityResponseType = HttpResponse<IUserGoalMmm>;
type EntityArrayResponseType = HttpResponse<IUserGoalMmm[]>;

@Injectable({ providedIn: 'root' })
export class UserGoalMmmService {
  public resourceUrl = SERVER_API_URL + 'api/user-goal-mmms';

  constructor(protected http: HttpClient) {}

  create(userGoalMmm: IUserGoalMmm): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userGoalMmm);
    return this.http
      .post<IUserGoalMmm>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(userGoalMmm: IUserGoalMmm): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userGoalMmm);
    return this.http
      .put<IUserGoalMmm>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUserGoalMmm>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUserGoalMmm[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(userGoalMmm: IUserGoalMmm): IUserGoalMmm {
    const copy: IUserGoalMmm = Object.assign({}, userGoalMmm, {
      createdDate: userGoalMmm.createdDate && userGoalMmm.createdDate.isValid() ? userGoalMmm.createdDate.format(DATE_FORMAT) : undefined,
      lastUpdateDate:
        userGoalMmm.lastUpdateDate && userGoalMmm.lastUpdateDate.isValid() ? userGoalMmm.lastUpdateDate.format(DATE_FORMAT) : undefined
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
      res.body.forEach((userGoalMmm: IUserGoalMmm) => {
        userGoalMmm.createdDate = userGoalMmm.createdDate ? moment(userGoalMmm.createdDate) : undefined;
        userGoalMmm.lastUpdateDate = userGoalMmm.lastUpdateDate ? moment(userGoalMmm.lastUpdateDate) : undefined;
      });
    }
    return res;
  }
}
