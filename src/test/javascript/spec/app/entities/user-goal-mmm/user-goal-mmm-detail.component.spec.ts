import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MmmappTestModule } from '../../../test.module';
import { UserGoalMmmDetailComponent } from 'app/entities/user-goal-mmm/user-goal-mmm-detail.component';
import { UserGoalMmm } from 'app/shared/model/user-goal-mmm.model';

describe('Component Tests', () => {
  describe('UserGoalMmm Management Detail Component', () => {
    let comp: UserGoalMmmDetailComponent;
    let fixture: ComponentFixture<UserGoalMmmDetailComponent>;
    const route = ({ data: of({ userGoalMmm: new UserGoalMmm(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MmmappTestModule],
        declarations: [UserGoalMmmDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UserGoalMmmDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserGoalMmmDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userGoalMmm on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userGoalMmm).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
