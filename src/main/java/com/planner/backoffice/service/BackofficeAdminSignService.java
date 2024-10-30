package com.planner.backoffice.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.backoffice.dto.BackofficeAdminSignRequest;
import com.planner.backoffice.model.BackofficeAdmin;
import com.planner.backoffice.model.BackofficeAdminStatus;
import com.planner.backoffice.repository.BackofficeAdminRepository;
import com.planner.backoffice.util.PasswordUtil;

import jakarta.validation.Valid;

@Service
public class BackofficeAdminSignService {
  @Autowired
  private BackofficeAdminRepository backofficeAdminRepository;
  @Autowired
  private PasswordUtil passwordUtil;

  public BackofficeAdmin signup(BackofficeAdminSignRequest reqDto) {
    Optional<BackofficeAdmin> existingAdmin = backofficeAdminRepository.findByAccountId(reqDto.getAccountId());
    if (existingAdmin.isPresent()) {
      throw new RuntimeException("이미 존재하는 계정입니다.");
    }

    String hashedPassword = passwordUtil.hashPassword(reqDto.getPassword());

    BackofficeAdmin admin = new BackofficeAdmin();
    admin.setAccountId(reqDto.getAccountId());
    admin.setHashedPassword(hashedPassword);
    admin.setCreatedAt(Instant.now());
    admin.setStatus(BackofficeAdminStatus.PENDING);
    backofficeAdminRepository.save(admin);

    return admin;
  }

  public BackofficeAdmin signin(@Valid BackofficeAdminSignRequest reqDto) {
    Optional<BackofficeAdmin> existingAdmin = backofficeAdminRepository.findByAccountId(reqDto.getAccountId());
    if (!existingAdmin.isPresent()) {
      throw new RuntimeException("존재하지 않는 계정입니다.");
    }

    BackofficeAdmin admin = existingAdmin.get();
    if (!passwordUtil.verifyPassword(reqDto.getPassword(), admin.getHashedPassword())) {
      throw new RuntimeException("비밀번호가 일치하지 않습니다.");
    }
    return admin;
  }
}
