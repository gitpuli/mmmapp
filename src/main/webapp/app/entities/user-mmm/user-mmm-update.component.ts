import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserMmm, UserMmm } from 'app/shared/model/user-mmm.model';
import { UserMmmService } from './user-mmm.service';

@Component({
  selector: 'jhi-user-mmm-update',
  templateUrl: './user-mmm-update.component.html'
})
export class UserMmmUpdateComponent implements OnInit {
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

  constructor(protected userMmmService: UserMmmService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userMmm }) => {
      this.updateForm(userMmm);
    });
  }

  updateForm(userMmm: IUserMmm): void {
    this.editForm.patchValue({
      id: userMmm.id,
      rightAnkle: userMmm.rightAnkle,
      leftAnkle: userMmm.leftAnkle,
      rightCalf: userMmm.rightCalf,
      leftCalf: userMmm.leftCalf,
      rightThigh: userMmm.rightThigh,
      leftThigh: userMmm.leftThigh,
      rightArm: userMmm.rightArm,
      leftArm: userMmm.leftArm,
      hips: userMmm.hips,
      waist: userMmm.waist,
      bust: userMmm.bust,
      createdDate: userMmm.createdDate,
      createdBy: userMmm.createdBy,
      lastUpdateDate: userMmm.lastUpdateDate,
      lastUpdateBy: userMmm.lastUpdateBy
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userMmm = this.createFromForm();
    if (userMmm.id !== undefined) {
      this.subscribeToSaveResponse(this.userMmmService.update(userMmm));
    } else {
      this.subscribeToSaveResponse(this.userMmmService.create(userMmm));
    }
  }

  private createFromForm(): IUserMmm {
    return {
      ...new UserMmm(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserMmm>>): void {
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
