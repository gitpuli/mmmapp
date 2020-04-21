import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MmmappTestModule } from '../../../test.module';
import { UserGoalMmmComponent } from 'app/entities/user-goal-mmm/user-goal-mmm.component';
import { UserGoalMmmService } from 'app/entities/user-goal-mmm/user-goal-mmm.service';
import { UserGoalMmm } from 'app/shared/model/user-goal-mmm.model';

describe('Component Tests', () => {
  describe('UserGoalMmm Management Component', () => {
    let comp: UserGoalMmmComponent;
    let fixture: ComponentFixture<UserGoalMmmComponent>;
    let service: UserGoalMmmService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MmmappTestModule],
        declarations: [UserGoalMmmComponent]
      })
        .overrideTemplate(UserGoalMmmComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserGoalMmmComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserGoalMmmService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserGoalMmm(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userGoalMmms && comp.userGoalMmms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
