import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserMmm, UserMmm } from 'app/shared/model/user-mmm.model';
import { UserMmmService } from './user-mmm.service';
import { UserMmmComponent } from './user-mmm.component';
import { UserMmmDetailComponent } from './user-mmm-detail.component';
import { UserMmmUpdateComponent } from './user-mmm-update.component';

@Injectable({ providedIn: 'root' })
export class UserMmmResolve implements Resolve<IUserMmm> {
  constructor(private service: UserMmmService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserMmm> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userMmm: HttpResponse<UserMmm>) => {
          if (userMmm.body) {
            return of(userMmm.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserMmm());
  }
}

export const userMmmRoute: Routes = [
  {
    path: '',
    component: UserMmmComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mmmappApp.userMmm.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UserMmmDetailComponent,
    resolve: {
      userMmm: UserMmmResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mmmappApp.userMmm.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UserMmmUpdateComponent,
    resolve: {
      userMmm: UserMmmResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mmmappApp.userMmm.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UserMmmUpdateComponent,
    resolve: {
      userMmm: UserMmmResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mmmappApp.userMmm.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
