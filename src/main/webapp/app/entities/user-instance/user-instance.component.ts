import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserInstance } from 'app/shared/model/user-instance.model';
import { UserInstanceService } from './user-instance.service';
import { UserInstanceDeleteDialogComponent } from './user-instance-delete-dialog.component';

@Component({
  selector: 'jhi-user-instance',
  templateUrl: './user-instance.component.html'
})
export class UserInstanceComponent implements OnInit, OnDestroy {
  userInstances?: IUserInstance[];
  eventSubscriber?: Subscription;

  constructor(
    protected userInstanceService: UserInstanceService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.userInstanceService.query().subscribe((res: HttpResponse<IUserInstance[]>) => (this.userInstances = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserInstances();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserInstance): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserInstances(): void {
    this.eventSubscriber = this.eventManager.subscribe('userInstanceListModification', () => this.loadAll());
  }

  delete(userInstance: IUserInstance): void {
    const modalRef = this.modalService.open(UserInstanceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userInstance = userInstance;
  }
}
