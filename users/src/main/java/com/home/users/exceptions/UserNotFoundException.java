package com.home.users.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotFoundException extends RuntimeException {
    private String message = "User not found";
}
