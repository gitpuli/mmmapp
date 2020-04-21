package com.oracle.mmm.repository;

import com.oracle.mmm.domain.UserMmm;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserMmm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserMmmRepository extends JpaRepository<UserMmm, Long> {
}
