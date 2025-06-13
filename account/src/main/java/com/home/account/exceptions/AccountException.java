package com.home.account.exceptions;

public class AccountException extends RuntimeException {
    String message;
    Throwable throwable;

    public AccountException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
        this.throwable = throwable;
    }
}
