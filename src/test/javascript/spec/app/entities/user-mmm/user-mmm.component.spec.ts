import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MmmappTestModule } from '../../../test.module';
import { UserMmmComponent } from 'app/entities/user-mmm/user-mmm.component';
import { UserMmmService } from 'app/entities/user-mmm/user-mmm.service';
import { UserMmm } from 'app/shared/model/user-mmm.model';

describe('Component Tests', () => {
  describe('UserMmm Management Component', () => {
    let comp: UserMmmComponent;
    let fixture: ComponentFixture<UserMmmComponent>;
    let service: UserMmmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MmmappTestModule],
        declarations: [UserMmmComponent]
      })
        .overrideTemplate(UserMmmComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserMmmComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserMmmService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserMmm(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userMmms && comp.userMmms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
