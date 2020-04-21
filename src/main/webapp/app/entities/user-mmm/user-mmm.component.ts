import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserMmm } from 'app/shared/model/user-mmm.model';
import { UserMmmService } from './user-mmm.service';
import { UserMmmDeleteDialogComponent } from './user-mmm-delete-dialog.component';

@Component({
  selector: 'jhi-user-mmm',
  templateUrl: './user-mmm.component.html'
})
export class UserMmmComponent implements OnInit, OnDestroy {
  userMmms?: IUserMmm[];
  eventSubscriber?: Subscription;

  constructor(protected userMmmService: UserMmmService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.userMmmService.query().subscribe((res: HttpResponse<IUserMmm[]>) => (this.userMmms = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserMmms();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserMmm): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserMmms(): void {
    this.eventSubscriber = this.eventManager.subscribe('userMmmListModification', () => this.loadAll());
  }

  delete(userMmm: IUserMmm): void {
    const modalRef = this.modalService.open(UserMmmDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userMmm = userMmm;
  }
}
