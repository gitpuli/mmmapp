import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MmmappSharedModule } from 'app/shared/shared.module';
import { UserInstanceComponent } from './user-instance.component';
import { UserInstanceDetailComponent } from './user-instance-detail.component';
import { UserInstanceUpdateComponent } from './user-instance-update.component';
import { UserInstanceDeleteDialogComponent } from './user-instance-delete-dialog.component';
import { userInstanceRoute } from './user-instance.route';

@NgModule({
  imports: [MmmappSharedModule, RouterModule.forChild(userInstanceRoute)],
  declarations: [UserInstanceComponent, UserInstanceDetailComponent, UserInstanceUpdateComponent, UserInstanceDeleteDialogComponent],
  entryComponents: [UserInstanceDeleteDialogComponent]
})
export class MmmappUserInstanceModule {}
