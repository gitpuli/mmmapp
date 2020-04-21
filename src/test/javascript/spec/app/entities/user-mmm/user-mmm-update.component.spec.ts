import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MmmappTestModule } from '../../../test.module';
import { UserMmmUpdateComponent } from 'app/entities/user-mmm/user-mmm-update.component';
import { UserMmmService } from 'app/entities/user-mmm/user-mmm.service';
import { UserMmm } from 'app/shared/model/user-mmm.model';

describe('Component Tests', () => {
  describe('UserMmm Management Update Component', () => {
    let comp: UserMmmUpdateComponent;
    let fixture: ComponentFixture<UserMmmUpdateComponent>;
    let service: UserMmmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MmmappTestModule],
        declarations: [UserMmmUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UserMmmUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserMmmUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserMmmService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserMmm(123);
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
        const entity = new UserMmm();
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
