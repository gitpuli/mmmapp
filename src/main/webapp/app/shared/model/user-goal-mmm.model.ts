import { Moment } from 'moment';

export interface IUserGoalMmm {
  id?: number;
  rightAnkle?: number;
  leftAnkle?: number;
  rightCalf?: number;
  leftCalf?: number;
  rightThigh?: number;
  leftThigh?: number;
  rightArm?: number;
  leftArm?: number;
  hips?: number;
  waist?: number;
  bust?: number;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdateDate?: Moment;
  lastUpdateBy?: string;
}

export class UserGoalMmm implements IUserGoalMmm {
  constructor(
    public id?: number,
    public rightAnkle?: number,
    public leftAnkle?: number,
    public rightCalf?: number,
    public leftCalf?: number,
    public rightThigh?: number,
    public leftThigh?: number,
    public rightArm?: number,
    public leftArm?: number,
    public hips?: number,
    public waist?: number,
    public bust?: number,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdateDate?: Moment,
    public lastUpdateBy?: string
  ) {}
}
