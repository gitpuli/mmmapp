import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MmmappTestModule } from '../../../test.module';
import { UserInstanceDetailComponent } from 'app/entities/user-instance/user-instance-detail.component';
import { UserInstance } from 'app/shared/model/user-instance.model';

describe('Component Tests', () => {
  describe('UserInstance Management Detail Component', () => {
    let comp: UserInstanceDetailComponent;
    let fixture: ComponentFixture<UserInstanceDetailComponent>;
    const route = ({ data: of({ userInstance: new UserInstance(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MmmappTestModule],
        declarations: [UserInstanceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UserInstanceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserInstanceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load userInstance on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userInstance).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
