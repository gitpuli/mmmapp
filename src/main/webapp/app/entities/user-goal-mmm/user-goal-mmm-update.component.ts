import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserGoalMmm, UserGoalMmm } from 'app/shared/model/user-goal-mmm.model';
import { UserGoalMmmService } from './user-goal-mmm.service';

@Component({
  selector: 'jhi-user-goal-mmm-update',
  templateUrl: './user-goal-mmm-update.component.html'
})
export class UserGoalMmmUpdateComponent implements OnInit {
  isSaving = false;
  createdDateDp: any;
  lastUpdateDateDp: any;

  editForm = this.fb.group({
    id: [],
    rightAnkle: [],
    leftAnkle: [],
    rightCalf: [],
    leftCalf: [],
    rightThigh: [],
    leftThigh: [],
    rightArm: [],
    leftArm: [],
    hips: [],
    waist: [],
    bust: [],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdateDate: [null, [Validators.required]],
    lastUpdateBy: [null, [Validators.required]]
  });

  constructor(protected userGoalMmmService: UserGoalMmmService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userGoalMmm }) => {
      this.updateForm(userGoalMmm);
    });
  }

  updateForm(userGoalMmm: IUserGoalMmm): void {
    this.editForm.patchValue({
      id: userGoalMmm.id,
      rightAnkle: userGoalMmm.rightAnkle,
      leftAnkle: userGoalMmm.leftAnkle,
      rightCalf: userGoalMmm.rightCalf,
      leftCalf: userGoalMmm.leftCalf,
      rightThigh: userGoalMmm.rightThigh,
      leftThigh: userGoalMmm.leftThigh,
      rightArm: userGoalMmm.rightArm,
      leftArm: userGoalMmm.leftArm,
      hips: userGoalMmm.hips,
      waist: userGoalMmm.waist,
      bust: userGoalMmm.bust,
      createdDate: userGoalMmm.createdDate,
      createdBy: userGoalMmm.createdBy,
      lastUpdateDate: userGoalMmm.lastUpdateDate,
      lastUpdateBy: userGoalMmm.lastUpdateBy
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userGoalMmm = this.createFromForm();
    if (userGoalMmm.id !== undefined) {
      this.subscribeToSaveResponse(this.userGoalMmmService.update(userGoalMmm));
    } else {
      this.subscribeToSaveResponse(this.userGoalMmmService.create(userGoalMmm));
    }
  }

  private createFromForm(): IUserGoalMmm {
    return {
      ...new UserGoalMmm(),
      id: this.editForm.get(['id'])!.value,
      rightAnkle: this.editForm.get(['rightAnkle'])!.value,
      leftAnkle: this.editForm.get(['leftAnkle'])!.value,
      rightCalf: this.editForm.get(['rightCalf'])!.value,
      leftCalf: this.editForm.get(['leftCalf'])!.value,
      rightThigh: this.editForm.get(['rightThigh'])!.value,
      leftThigh: this.editForm.get(['leftThigh'])!.value,
      rightArm: this.editForm.get(['rightArm'])!.value,
      leftArm: this.editForm.get(['leftArm'])!.value,
      hips: this.editForm.get(['hips'])!.value,
      waist: this.editForm.get(['waist'])!.value,
      bust: this.editForm.get(['bust'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdateDate: this.editForm.get(['lastUpdateDate'])!.value,
      lastUpdateBy: this.editForm.get(['lastUpdateBy'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserGoalMmm>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
