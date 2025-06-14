package com.home.users.exceptions;

public class UserException extends RuntimeException {

  public UserException() {
    super();
  }

  public UserException(String message) {
    super(message);
  }

  public UserException(String message, Throwable cause) {
    super(message, cause);
  }
}
