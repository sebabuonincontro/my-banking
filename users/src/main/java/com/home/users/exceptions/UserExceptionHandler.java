package com.home.users.exceptions;

import com.google.common.collect.ImmutableMap;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  public Object handlerUserNotFound(UserNotFoundException ex) {
    return buildBody("404", "NOT FOUND", ex.getMessage() + " NOT FOUND");
  }

  private Object buildBody(String code, String status, String description) {
    val response = ImmutableMap.builder();
    response.put("code", code);
    response.put("status", status);
    response.put("description", description);
    return response;
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({UserException.class, })
  public Object handlerUserException(UserException ex) {
    return buildBody("500", "INTERNAL SERVER ERROR", ex.getMessage());
  }
}
