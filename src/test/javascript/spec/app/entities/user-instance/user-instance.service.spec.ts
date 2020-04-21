import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { UserInstanceService } from 'app/entities/user-instance/user-instance.service';
import { IUserInstance, UserInstance } from 'app/shared/model/user-instance.model';

describe('Service Tests', () => {
  describe('UserInstance Service', () => {
    let injector: TestBed;
    let service: UserInstanceService;
    let httpMock: HttpTestingController;
    let elemDefault: IUserInstance;
    let expectedResult: IUserInstance | IUserInstance[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UserInstanceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new UserInstance(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', currentDate, 'AAAAAAA');
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

      it('should create a UserInstance', () => {
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

        service.create(new UserInstance()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UserInstance', () => {
        const returnedFromService = Object.assign(
          {
            userName: 'BBBBBB',
            userEmail: 'BBBBBB',
            userPhone: 'BBBBBB',
            password: 'BBBBBB',
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

      it('should return a list of UserInstance', () => {
        const returnedFromService = Object.assign(
          {
            userName: 'BBBBBB',
            userEmail: 'BBBBBB',
            userPhone: 'BBBBBB',
            password: 'BBBBBB',
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

      it('should delete a UserInstance', () => {
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
