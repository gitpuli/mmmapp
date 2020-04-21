import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MmmappTestModule } from '../../../test.module';
import { UserGoalMmmUpdateComponent } from 'app/entities/user-goal-mmm/user-goal-mmm-update.component';
import { UserGoalMmmService } from 'app/entities/user-goal-mmm/user-goal-mmm.service';
import { UserGoalMmm } from 'app/shared/model/user-goal-mmm.model';

describe('Component Tests', () => {
  describe('UserGoalMmm Management Update Component', () => {
    let comp: UserGoalMmmUpdateComponent;
    let fixture: ComponentFixture<UserGoalMmmUpdateComponent>;
    let service: UserGoalMmmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MmmappTestModule],
        declarations: [UserGoalMmmUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UserGoalMmmUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserGoalMmmUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserGoalMmmService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserGoalMmm(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserGoalMmm();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
