package com.oracle.mmm.repository;

import com.oracle.mmm.domain.UserInstance;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserInstanceRepository extends JpaRepository<UserInstance, Long> {
}
