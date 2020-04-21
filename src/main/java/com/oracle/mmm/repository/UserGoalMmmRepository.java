package com.oracle.mmm.repository;

import com.oracle.mmm.domain.UserGoalMmm;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserGoalMmm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserGoalMmmRepository extends JpaRepository<UserGoalMmm, Long> {
}
