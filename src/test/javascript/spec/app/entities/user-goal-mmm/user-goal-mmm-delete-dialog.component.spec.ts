import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MmmappTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { UserGoalMmmDeleteDialogComponent } from 'app/entities/user-goal-mmm/user-goal-mmm-delete-dialog.component';
import { UserGoalMmmService } from 'app/entities/user-goal-mmm/user-goal-mmm.service';

describe('Component Tests', () => {
  describe('UserGoalMmm Management Delete Component', () => {
    let comp: UserGoalMmmDeleteDialogComponent;
    let fixture: ComponentFixture<UserGoalMmmDeleteDialogComponent>;
    let service: UserGoalMmmService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MmmappTestModule],
        declarations: [UserGoalMmmDeleteDialogComponent]
      })
        .overrideTemplate(UserGoalMmmDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserGoalMmmDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserGoalMmmService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
