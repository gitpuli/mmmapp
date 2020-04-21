import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MmmappTestModule } from '../../../test.module';
import { UserInstanceComponent } from 'app/entities/user-instance/user-instance.component';
import { UserInstanceService } from 'app/entities/user-instance/user-instance.service';
import { UserInstance } from 'app/shared/model/user-instance.model';

describe('Component Tests', () => {
  describe('UserInstance Management Component', () => {
    let comp: UserInstanceComponent;
    let fixture: ComponentFixture<UserInstanceComponent>;
    let service: UserInstanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MmmappTestModule],
        declarations: [UserInstanceComponent]
      })
        .overrideTemplate(UserInstanceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserInstanceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserInstanceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserInstance(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userInstances && comp.userInstances[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
