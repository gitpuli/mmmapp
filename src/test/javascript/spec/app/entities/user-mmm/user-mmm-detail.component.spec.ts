import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MmmappTestModule } from '../../../test.module';
import { UserMmmDetailComponent } from 'app/entities/user-mmm/user-mmm-detail.component';
import { UserMmm } from 'app/shared/model/user-mmm.model';

describe('Component Tests', () => {
  describe('UserMmm Management Detail Component', () => {
    let comp: UserMmmDetailComponent;
    let fixture: ComponentFixture<UserMmmDetailComponent>;
    const route = ({ data: of({ userMmm: new UserMmm(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MmmappTestModule],
        declarations: [UserMmmDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UserMmmDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserMmmDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userMmm on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userMmm).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
