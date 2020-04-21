import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserInstance, UserInstance } from 'app/shared/model/user-instance.model';
import { UserInstanceService } from './user-instance.service';
import { UserInstanceComponent } from './user-instance.component';
import { UserInstanceDetailComponent } from './user-instance-detail.component';
import { UserInstanceUpdateComponent } from './user-instance-update.component';

@Injectable({ providedIn: 'root' })
export class UserInstanceResolve implements Resolve<IUserInstance> {
  constructor(private service: UserInstanceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserInstance> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userInstance: HttpResponse<UserInstance>) => {
          if (userInstance.body) {
            return of(userInstance.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserInstance());
  }
}

export const userInstanceRoute: Routes = [
  {
    path: '',
    component: UserInstanceComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mmmappApp.userInstance.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UserInstanceDetailComponent,
    resolve: {
      userInstance: UserInstanceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mmmappApp.userInstance.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UserInstanceUpdateComponent,
    resolve: {
      userInstance: UserInstanceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mmmappApp.userInstance.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UserInstanceUpdateComponent,
    resolve: {
      userInstance: UserInstanceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'mmmappApp.userInstance.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
