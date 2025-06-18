package com.home.banking.transaction.domain.ports.in;

import com.home.banking.transaction.domain.User;

import java.util.Optional;

public interface UserUseCase {

    Optional<User> retrieveUserBy(Long userId);
}
