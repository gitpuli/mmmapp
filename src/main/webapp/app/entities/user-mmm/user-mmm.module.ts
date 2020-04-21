import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MmmappSharedModule } from 'app/shared/shared.module';
import { UserMmmComponent } from './user-mmm.component';
import { UserMmmDetailComponent } from './user-mmm-detail.component';
import { UserMmmUpdateComponent } from './user-mmm-update.component';
import { UserMmmDeleteDialogComponent } from './user-mmm-delete-dialog.component';
import { userMmmRoute } from './user-mmm.route';

@NgModule({
  imports: [MmmappSharedModule, RouterModule.forChild(userMmmRoute)],
  declarations: [UserMmmComponent, UserMmmDetailComponent, UserMmmUpdateComponent, UserMmmDeleteDialogComponent],
  entryComponents: [UserMmmDeleteDialogComponent]
})
export class MmmappUserMmmModule {}
