package com.company.githubrepositoryapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {

  private int code;
  private String message;
}
