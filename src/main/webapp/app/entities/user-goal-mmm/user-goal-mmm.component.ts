import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserGoalMmm } from 'app/shared/model/user-goal-mmm.model';
import { UserGoalMmmService } from './user-goal-mmm.service';
import { UserGoalMmmDeleteDialogComponent } from './user-goal-mmm-delete-dialog.component';

@Component({
  selector: 'jhi-user-goal-mmm',
  templateUrl: './user-goal-mmm.component.html'
})
export class UserGoalMmmComponent implements OnInit, OnDestroy {
  userGoalMmms?: IUserGoalMmm[];
  eventSubscriber?: Subscription;

  constructor(
    protected userGoalMmmService: UserGoalMmmService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.userGoalMmmService.query().subscribe((res: HttpResponse<IUserGoalMmm[]>) => (this.userGoalMmms = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserGoalMmms();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserGoalMmm): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserGoalMmms(): void {
    this.eventSubscriber = this.eventManager.subscribe('userGoalMmmListModification', () => this.loadAll());
  }

  delete(userGoalMmm: IUserGoalMmm): void {
    const modalRef = this.modalService.open(UserGoalMmmDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userGoalMmm = userGoalMmm;
  }
}
