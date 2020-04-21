import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserMmm } from 'app/shared/model/user-mmm.model';

@Component({
  selector: 'jhi-user-mmm-detail',
  templateUrl: './user-mmm-detail.component.html'
})
export class UserMmmDetailComponent implements OnInit {
  userMmm: IUserMmm | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userMmm }) => (this.userMmm = userMmm));
  }

  previousState(): void {
    window.history.back();
  }
}
