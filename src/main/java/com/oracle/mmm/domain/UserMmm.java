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
 * The Usermmm entity.
 */
@ApiModel(description = "The Usermmm entity.")
@Entity
@Table(name = "user_mmm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserMmm implements Serializable {

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

    public UserMmm rightAnkle(Integer rightAnkle) {
        this.rightAnkle = rightAnkle;
        return this;
    }

    public void setRightAnkle(Integer rightAnkle) {
        this.rightAnkle = rightAnkle;
    }

    public Integer getLeftAnkle() {
        return leftAnkle;
    }

    public UserMmm leftAnkle(Integer leftAnkle) {
        this.leftAnkle = leftAnkle;
        return this;
    }

    public void setLeftAnkle(Integer leftAnkle) {
        this.leftAnkle = leftAnkle;
    }

    public Integer getRightCalf() {
        return rightCalf;
    }

    public UserMmm rightCalf(Integer rightCalf) {
        this.rightCalf = rightCalf;
        return this;
    }

    public void setRightCalf(Integer rightCalf) {
        this.rightCalf = rightCalf;
    }

    public Integer getLeftCalf() {
        return leftCalf;
    }

    public UserMmm leftCalf(Integer leftCalf) {
        this.leftCalf = leftCalf;
        return this;
    }

    public void setLeftCalf(Integer leftCalf) {
        this.leftCalf = leftCalf;
    }

    public Integer getRightThigh() {
        return rightThigh;
    }

    public UserMmm rightThigh(Integer rightThigh) {
        this.rightThigh = rightThigh;
        return this;
    }

    public void setRightThigh(Integer rightThigh) {
        this.rightThigh = rightThigh;
    }

    public Integer getLeftThigh() {
        return leftThigh;
    }

    public UserMmm leftThigh(Integer leftThigh) {
        this.leftThigh = leftThigh;
        return this;
    }

    public void setLeftThigh(Integer leftThigh) {
        this.leftThigh = leftThigh;
    }

    public Integer getRightArm() {
        return rightArm;
    }

    public UserMmm rightArm(Integer rightArm) {
        this.rightArm = rightArm;
        return this;
    }

    public void setRightArm(Integer rightArm) {
        this.rightArm = rightArm;
    }

    public Integer getLeftArm() {
        return leftArm;
    }

    public UserMmm leftArm(Integer leftArm) {
        this.leftArm = leftArm;
        return this;
    }

    public void setLeftArm(Integer leftArm) {
        this.leftArm = leftArm;
    }

    public Integer getHips() {
        return hips;
    }

    public UserMmm hips(Integer hips) {
        this.hips = hips;
        return this;
    }

    public void setHips(Integer hips) {
        this.hips = hips;
    }

    public Integer getWaist() {
        return waist;
    }

    public UserMmm waist(Integer waist) {
        this.waist = waist;
        return this;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public Integer getBust() {
        return bust;
    }

    public UserMmm bust(Integer bust) {
        this.bust = bust;
        return this;
    }

    public void setBust(Integer bust) {
        this.bust = bust;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public UserMmm createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public UserMmm createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public UserMmm lastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
        return this;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public UserMmm lastUpdateBy(String lastUpdateBy) {
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
        if (!(o instanceof UserMmm)) {
            return false;
        }
        return id != null && id.equals(((UserMmm) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserMmm{" +
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
