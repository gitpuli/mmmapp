package com.oracle.mmm.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * The UserGoalmmm entity.
 */
@ApiModel(description = "The UserGoalmmm entity.")
@Entity
@Table(name = "user_goal_mmm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserGoalMmm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "right_ankle")
    private Integer rightAnkle;

    @Column(name = "left_ankle")
    private Integer leftAnkle;

    @Column(name = "right_calf")
    private Integer rightCalf;

    @Column(name = "left_calf")
    private Integer leftCalf;

    @Column(name = "right_thigh")
    private Integer rightThigh;

    @Column(name = "left_thigh")
    private Integer leftThigh;

    @Column(name = "right_arm")
    private Integer rightArm;

    @Column(name = "left_arm")
    private Integer leftArm;

    @Column(name = "hips")
    private Integer hips;

    @Column(name = "waist")
    private Integer waist;

    @Column(name = "bust")
    private Integer bust;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "last_update_date", nullable = false)
    private LocalDate lastUpdateDate;

    @NotNull
    @Column(name = "last_update_by", nullable = false)
    private String lastUpdateBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRightAnkle() {
        return rightAnkle;
    }

    public UserGoalMmm rightAnkle(Integer rightAnkle) {
        this.rightAnkle = rightAnkle;
        return this;
    }

    public void setRightAnkle(Integer rightAnkle) {
        this.rightAnkle = rightAnkle;
    }

    public Integer getLeftAnkle() {
        return leftAnkle;
    }

    public UserGoalMmm leftAnkle(Integer leftAnkle) {
        this.leftAnkle = leftAnkle;
        return this;
    }

    public void setLeftAnkle(Integer leftAnkle) {
        this.leftAnkle = leftAnkle;
    }

    public Integer getRightCalf() {
        return rightCalf;
    }

    public UserGoalMmm rightCalf(Integer rightCalf) {
        this.rightCalf = rightCalf;
        return this;
    }

    public void setRightCalf(Integer rightCalf) {
        this.rightCalf = rightCalf;
    }

    public Integer getLeftCalf() {
        return leftCalf;
    }

    public UserGoalMmm leftCalf(Integer leftCalf) {
        this.leftCalf = leftCalf;
        return this;
    }

    public void setLeftCalf(Integer leftCalf) {
        this.leftCalf = leftCalf;
    }

    public Integer getRightThigh() {
        return rightThigh;
    }

    public UserGoalMmm rightThigh(Integer rightThigh) {
        this.rightThigh = rightThigh;
        return this;
    }

    public void setRightThigh(Integer rightThigh) {
        this.rightThigh = rightThigh;
    }

    public Integer getLeftThigh() {
        return leftThigh;
    }

    public UserGoalMmm leftThigh(Integer leftThigh) {
        this.leftThigh = leftThigh;
        return this;
    }

    public void setLeftThigh(Integer leftThigh) {
        this.leftThigh = leftThigh;
    }

    public Integer getRightArm() {
        return rightArm;
    }

    public UserGoalMmm rightArm(Integer rightArm) {
        this.rightArm = rightArm;
        return this;
    }

    public void setRightArm(Integer rightArm) {
        this.rightArm = rightArm;
    }

    public Integer getLeftArm() {
        return leftArm;
    }

    public UserGoalMmm leftArm(Integer leftArm) {
        this.leftArm = leftArm;
        return this;
    }

    public void setLeftArm(Integer leftArm) {
        this.leftArm = leftArm;
    }

    public Integer getHips() {
        return hips;
    }

    public UserGoalMmm hips(Integer hips) {
        this.hips = hips;
        return this;
    }

    public void setHips(Integer hips) {
        this.hips = hips;
    }

    public Integer getWaist() {
        return waist;
    }

    public UserGoalMmm waist(Integer waist) {
        this.waist = waist;
        return this;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public Integer getBust() {
        return bust;
    }

    public UserGoalMmm bust(Integer bust) {
        this.bust = bust;
        return this;
    }

    public void setBust(Integer bust) {
        this.bust = bust;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public UserGoalMmm createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public UserGoalMmm createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public UserGoalMmm lastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public UserGoalMmm lastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
        return this;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserGoalMmm)) {
            return false;
        }
        return id != null && id.equals(((UserGoalMmm) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserGoalMmm{" +
            "id=" + getId() +
            ", rightAnkle=" + getRightAnkle() +
            ", leftAnkle=" + getLeftAnkle() +
            ", rightCalf=" + getRightCalf() +
            ", leftCalf=" + getLeftCalf() +
            ", rightThigh=" + getRightThigh() +
            ", leftThigh=" + getLeftThigh() +
            ", rightArm=" + getRightArm() +
            ", leftArm=" + getLeftArm() +
            ", hips=" + getHips() +
            ", waist=" + getWaist() +
            ", bust=" + getBust() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastUpdateDate='" + getLastUpdateDate() + "'" +
            ", lastUpdateBy='" + getLastUpdateBy() + "'" +
            "}";
    }
}
