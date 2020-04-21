import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MmmappTestModule } from '../../../test.module';
import { UserInstanceUpdateComponent } from 'app/entities/user-instance/user-instance-update.component';
import { UserInstanceService } from 'app/entities/user-instance/user-instance.service';
import { UserInstance } from 'app/shared/model/user-instance.model';

describe('Component Tests', () => {
  describe('UserInstance Management Update Component', () => {
    let comp: UserInstanceUpdateComponent;
    let fixture: ComponentFixture<UserInstanceUpdateComponent>;
    let service: UserInstanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MmmappTestModule],
        declarations: [UserInstanceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UserInstanceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserInstanceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserInstanceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserInstance(123);
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
        const entity = new UserInstance();
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
