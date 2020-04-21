import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserInstance, UserInstance } from 'app/shared/model/user-instance.model';
import { UserInstanceService } from './user-instance.service';

@Component({
  selector: 'jhi-user-instance-update',
  templateUrl: './user-instance-update.component.html'
})
export class UserInstanceUpdateComponent implements OnInit {
  isSaving = false;
  createdDateDp: any;
  lastUpdateDateDp: any;

  editForm = this.fb.group({
    id: [],
    userName: [null, [Validators.required]],
    userEmail: [null, [Validators.required]],
    userPhone: [],
    password: [null, [Validators.required]],
    createdDate: [null, [Validators.required]],
    createdBy: [null, [Validators.required]],
    lastUpdateDate: [null, [Validators.required]],
    lastUpdateBy: [null, [Validators.required]]
  });

  constructor(protected userInstanceService: UserInstanceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userInstance }) => {
      this.updateForm(userInstance);
    });
  }

  updateForm(userInstance: IUserInstance): void {
    this.editForm.patchValue({
      id: userInstance.id,
      userName: userInstance.userName,
      userEmail: userInstance.userEmail,
      userPhone: userInstance.userPhone,
      password: userInstance.password,
      createdDate: userInstance.createdDate,
      createdBy: userInstance.createdBy,
      lastUpdateDate: userInstance.lastUpdateDate,
      lastUpdateBy: userInstance.lastUpdateBy
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userInstance = this.createFromForm();
    if (userInstance.id !== undefined) {
      this.subscribeToSaveResponse(this.userInstanceService.update(userInstance));
    } else {
      this.subscribeToSaveResponse(this.userInstanceService.create(userInstance));
    }
  }

  private createFromForm(): IUserInstance {
    return {
      ...new UserInstance(),
      id: this.editForm.get(['id'])!.value,
      userName: this.editForm.get(['userName'])!.value,
      userEmail: this.editForm.get(['userEmail'])!.value,
      userPhone: this.editForm.get(['userPhone'])!.value,
      password: this.editForm.get(['password'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastUpdateDate: this.editForm.get(['lastUpdateDate'])!.value,
      lastUpdateBy: this.editForm.get(['lastUpdateBy'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserInstance>>): void {
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
