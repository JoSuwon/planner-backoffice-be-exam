package com.planner.backoffice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.backoffice.model.BackofficeAdmin;

public interface BackofficeAdminRepository extends JpaRepository<BackofficeAdmin, Long> {
  Optional<BackofficeAdmin> findByAccountId(String accountId);
}
