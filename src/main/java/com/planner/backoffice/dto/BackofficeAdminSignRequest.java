package com.planner.backoffice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BackofficeAdminSignRequest {
  @NotBlank(message = "accountId 필드가 필요합니다")
  @Size(min = 8, max = 15, message = "accountId 필드는 8자 이상 15자 이하로 입력해주세요.")
  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "accountId 필드는 영문 대소문자와 숫자만 입력해주세요.")
  private String accountId;

  @NotBlank(message = "password 필드가 필요합니다")
  @Size(min = 10, max = 20, message = "password 필드는 10자 이상 20자 이하로 입력해주세요.")
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,20}$", message = "password 필드는 영문, 숫자, 특수문자(@,$,!,%,*,?,&)를 포함해야 합니다.")
  private String password;
}
