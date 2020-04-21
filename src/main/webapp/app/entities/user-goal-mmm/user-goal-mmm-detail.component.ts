import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserGoalMmm } from 'app/shared/model/user-goal-mmm.model';

@Component({
  selector: 'jhi-user-goal-mmm-detail',
  templateUrl: './user-goal-mmm-detail.component.html'
})
export class UserGoalMmmDetailComponent implements OnInit {
  userGoalMmm: IUserGoalMmm | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userGoalMmm }) => (this.userGoalMmm = userGoalMmm));
  }

  previousState(): void {
    window.history.back();
  }
}
