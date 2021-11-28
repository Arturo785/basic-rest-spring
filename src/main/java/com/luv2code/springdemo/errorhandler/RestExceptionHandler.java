package com.luv2code.springdemo.errorhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

  // catches any exception that happens in the requests for our custom one
  @ExceptionHandler
  public ResponseEntity<CustomErrorResponse> handleException(CustomHandlerException errorResponse) {
    CustomErrorResponse error =
        new CustomErrorResponse(
            HttpStatus.NOT_FOUND.value(), errorResponse.getMessage(), System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  // This one catches all the exceptions when there is not an specific catcher for it
  @ExceptionHandler
  public ResponseEntity<CustomErrorResponse> handleException(Exception errorResponse) {
    CustomErrorResponse error =
        new CustomErrorResponse(
            HttpStatus.BAD_REQUEST.value(), errorResponse.getMessage(), System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
