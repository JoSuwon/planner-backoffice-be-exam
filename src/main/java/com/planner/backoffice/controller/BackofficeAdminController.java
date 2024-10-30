package com.planner.backoffice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.backoffice.dto.BackofficeAdminSignRequest;
import com.planner.backoffice.model.BackofficeAdmin;
import com.planner.backoffice.model.BackofficeAdminStatus;
import com.planner.backoffice.service.BackofficeAdminSignService;
import com.planner.backoffice.util.JwtTokenProvider;

import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/admin")
public class BackofficeAdminController {
  @Autowired
  private BackofficeAdminSignService backofficeAdminSignService;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/signup")
  public ResponseEntity<Map<String, String>> signup(
      @Valid @RequestBody BackofficeAdminSignRequest reqDto) {
    try {
      BackofficeAdmin admin = backofficeAdminSignService.signup(reqDto);
      if (admin == null) {
        return ResponseEntity.badRequest().body(Map.of("status", "error", "message", "계정 생성에 실패하였습니다."));
      }
      return ResponseEntity.ok(Map.of(
          "status", "success",
          "message", "계정 생성에 성공하였습니다."));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(Map.of("status", "error", "message", e.getMessage()));
    }
  }

  @PostMapping("/signin")
  public ResponseEntity<Map<String, Object>> signin(
      @Valid @RequestBody BackofficeAdminSignRequest reqDto) {
    try {
      BackofficeAdmin admin = backofficeAdminSignService.signin(reqDto);
      if (admin.getStatus() == BackofficeAdminStatus.PENDING) {
        throw new Exception("관리자 승인 대기중입니다.");
      }
      String token = jwtTokenProvider.createToken(admin.getId());
      return ResponseEntity.ok(Map.of(
          "status", "success",
          "message", "로그인에 성공하였습니다.",
          "token", token));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(Map.of("status", "error", "message", e.getMessage()));
    }
  }
}
