import { Moment } from 'moment';

export interface IUserInstance {
  id?: number;
  userName?: string;
  userEmail?: string;
  userPhone?: string;
  password?: string;
  createdDate?: Moment;
  createdBy?: string;
  lastUpdateDate?: Moment;
  lastUpdateBy?: string;
}

export class UserInstance implements IUserInstance {
  constructor(
    public id?: number,
    public userName?: string,
    public userEmail?: string,
    public userPhone?: string,
    public password?: string,
    public createdDate?: Moment,
    public createdBy?: string,
    public lastUpdateDate?: Moment,
    public lastUpdateBy?: string
  ) {}
}
