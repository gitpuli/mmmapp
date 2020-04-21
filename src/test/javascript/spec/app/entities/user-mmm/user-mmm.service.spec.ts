import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { UserMmmService } from 'app/entities/user-mmm/user-mmm.service';
import { IUserMmm, UserMmm } from 'app/shared/model/user-mmm.model';

describe('Service Tests', () => {
  describe('UserMmm Service', () => {
    let injector: TestBed;
    let service: UserMmmService;
    let httpMock: HttpTestingController;
    let elemDefault: IUserMmm;
    let expectedResult: IUserMmm | IUserMmm[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UserMmmService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new UserMmm(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, currentDate, 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_FORMAT),
            lastUpdateDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a UserMmm', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdDate: currentDate.format(DATE_FORMAT),
            lastUpdateDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastUpdateDate: currentDate
          },
          returnedFromService
        );

        service.create(new UserMmm()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UserMmm', () => {
        const returnedFromService = Object.assign(
          {
            rightAnkle: 1,
            leftAnkle: 1,
            rightCalf: 1,
            leftCalf: 1,
            rightThigh: 1,
            leftThigh: 1,
            rightArm: 1,
            leftArm: 1,
            hips: 1,
            waist: 1,
            bust: 1,
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: 'BBBBBB',
            lastUpdateDate: currentDate.format(DATE_FORMAT),
            lastUpdateBy: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastUpdateDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of UserMmm', () => {
        const returnedFromService = Object.assign(
          {
            rightAnkle: 1,
            leftAnkle: 1,
            rightCalf: 1,
            leftCalf: 1,
            rightThigh: 1,
            leftThigh: 1,
            rightArm: 1,
            leftArm: 1,
            hips: 1,
            waist: 1,
            bust: 1,
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: 'BBBBBB',
            lastUpdateDate: currentDate.format(DATE_FORMAT),
            lastUpdateBy: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastUpdateDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a UserMmm', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
