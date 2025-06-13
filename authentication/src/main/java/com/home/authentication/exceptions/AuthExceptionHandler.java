package com.home.authentication.exceptions;

import com.google.common.collect.ImmutableMap;
import com.home.common.entities.AuthResponse;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthException.class)
    public Object handleUserExistException(AuthException ex) {
        return buildBody("400", "BAD REQUEST", ex.getMessage());
    }

    private Object buildBody(String code, String status, String description) {
        val response = ImmutableMap.builder();
        response.put("code", code);
        response.put("status", status);
        response.put("description", description);
        return response;
    }
}
