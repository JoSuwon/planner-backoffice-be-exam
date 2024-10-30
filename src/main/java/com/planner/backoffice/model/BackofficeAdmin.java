package com.planner.backoffice.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "backoffice_admin")
public class BackofficeAdmin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "account_id", nullable = false, unique = true)
  private String accountId;

  @Column(name = "hashed_password", nullable = false)
  private String hashedPassword;

  @Enumerated(EnumType.STRING)
  private BackofficeAdminStatus status;

  @Column(name = "created_at", nullable = false, columnDefinition = "timestamp")
  private Instant createdAt;
}
