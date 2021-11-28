package com.luv2code.springdemo.errorhandler;

public class CustomHandlerException extends RuntimeException {

  public CustomHandlerException() {}

  public CustomHandlerException(String message) {
    super(message);
  }

  public CustomHandlerException(String message, Throwable cause) {
    super(message, cause);
  }

  public CustomHandlerException(Throwable cause) {
    super(cause);
  }
}
