import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserMmm } from 'app/shared/model/user-mmm.model';
import { UserMmmService } from './user-mmm.service';

@Component({
  templateUrl: './user-mmm-delete-dialog.component.html'
})
export class UserMmmDeleteDialogComponent {
  userMmm?: IUserMmm;

  constructor(protected userMmmService: UserMmmService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userMmmService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userMmmListModification');
      this.activeModal.close();
    });
  }
}
