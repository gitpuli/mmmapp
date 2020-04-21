import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'user-mmm',
        loadChildren: () => import('./user-mmm/user-mmm.module').then(m => m.MmmappUserMmmModule)
      },
      {
        path: 'user-goal-mmm',
        loadChildren: () => import('./user-goal-mmm/user-goal-mmm.module').then(m => m.MmmappUserGoalMmmModule)
      },
      {
        path: 'user-instance',
        loadChildren: () => import('./user-instance/user-instance.module').then(m => m.MmmappUserInstanceModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class MmmappEntityModule {}
