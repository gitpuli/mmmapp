import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserInstance } from 'app/shared/model/user-instance.model';

@Component({
  selector: 'jhi-user-instance-detail',
  templateUrl: './user-instance-detail.component.html'
})
export class UserInstanceDetailComponent implements OnInit {
  userInstance: IUserInstance | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userInstance }) => (this.userInstance = userInstance));
  }

  previousState(): void {
    window.history.back();
  }
}
