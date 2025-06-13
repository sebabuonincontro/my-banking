package com.home.common.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    USER_ADMIN("USER_ADMIN"),
    AUTHOR_ADMIN("AUTHOR_ADMIN"),
    BOOK_ADMIN("BOOK_ADMIN");

    private final String role;

}
