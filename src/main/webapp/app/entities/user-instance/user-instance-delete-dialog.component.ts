import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserInstance } from 'app/shared/model/user-instance.model';
import { UserInstanceService } from './user-instance.service';

@Component({
  templateUrl: './user-instance-delete-dialog.component.html'
})
export class UserInstanceDeleteDialogComponent {
  userInstance?: IUserInstance;

  constructor(
    protected userInstanceService: UserInstanceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userInstanceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userInstanceListModification');
      this.activeModal.close();
    });
  }
}
