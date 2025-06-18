package com.home.banking.transaction.domain;

import lombok.Builder;

@Builder
public record User(Long id,
                   String username,
                   String password,
                   String email,
                   Boolean enabled,
                   String role) {
}
