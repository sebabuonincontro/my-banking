package com.home.authentication.exceptions;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

  private String username;

  public AuthException(String username, String message) {
    super(message);
    this.username = username;
  }
}
