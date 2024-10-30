package com.planner.backoffice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

  @Value("${BACKOFFICE_ADMIN_SALT}")
  private String salt;

  public String hashPassword(String password) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      String passwordWithSalt = password + salt;

      byte[] hashedBytes = md.digest(passwordWithSalt.getBytes());

      StringBuilder sb = new StringBuilder();
      for (byte b : hashedBytes) {
        sb.append(String.format("%02x", b));
      }

      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Failed to hash password", e);
    }
  }

  public boolean verifyPassword(String password, String hashedPassword) {
    String hashedCurrentPassword = hashPassword(password);
    return hashedCurrentPassword.equals(hashedPassword);
  }
}