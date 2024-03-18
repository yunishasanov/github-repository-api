package com.company.githubrepositoryapi.exception;

import com.company.githubrepositoryapi.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> resourceNotFoundException(IllegalArgumentException exc) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
        exc.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
        .body(exceptionResponse);
  }

  @ExceptionHandler(ThirdpartyServiceException.class)
  public ResponseEntity<?> resourceNotFoundException(ThirdpartyServiceException exc) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        HttpStatus.SERVICE_UNAVAILABLE.value(), exc.getMessage());
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
        .contentType(MediaType.APPLICATION_JSON)
        .body(exceptionResponse);
  }

}
