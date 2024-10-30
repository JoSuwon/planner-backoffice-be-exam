package com.planner.backoffice.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationErrorResponse {
  private List<String> message;
}
