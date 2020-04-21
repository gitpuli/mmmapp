import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MmmappSharedModule } from 'app/shared/shared.module';
import { UserGoalMmmComponent } from './user-goal-mmm.component';
import { UserGoalMmmDetailComponent } from './user-goal-mmm-detail.component';
import { UserGoalMmmUpdateComponent } from './user-goal-mmm-update.component';
import { UserGoalMmmDeleteDialogComponent } from './user-goal-mmm-delete-dialog.component';
import { userGoalMmmRoute } from './user-goal-mmm.route';

@NgModule({
  imports: [MmmappSharedModule, RouterModule.forChild(userGoalMmmRoute)],
  declarations: [UserGoalMmmComponent, UserGoalMmmDetailComponent, UserGoalMmmUpdateComponent, UserGoalMmmDeleteDialogComponent],
  entryComponents: [UserGoalMmmDeleteDialogComponent]
})
export class MmmappUserGoalMmmModule {}
