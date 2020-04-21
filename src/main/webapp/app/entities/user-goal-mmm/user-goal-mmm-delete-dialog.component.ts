import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserGoalMmm } from 'app/shared/model/user-goal-mmm.model';
import { UserGoalMmmService } from './user-goal-mmm.service';

@Component({
  templateUrl: './user-goal-mmm-delete-dialog.component.html'
})
export class UserGoalMmmDeleteDialogComponent {
  userGoalMmm?: IUserGoalMmm;

  constructor(
    protected userGoalMmmService: UserGoalMmmService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userGoalMmmService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userGoalMmmListModification');
      this.activeModal.close();
    });
  }
}
