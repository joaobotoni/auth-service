package com.botoni.vistoo.rest.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ProblemDetail> handleInternalServerError(RuntimeException exception) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ProblemDetail> handleForbidden(AccessDeniedException exception) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "Access denied");
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(problem);
  }

  @ExceptionHandler(HttpClientErrorException.NotFound.class)
  public ResponseEntity<ProblemDetail> handleNotFound(HttpClientErrorException.NotFound exception) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
  }
}
