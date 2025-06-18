package com.home.banking.transaction.infrastructure.adapters;

import com.home.banking.transaction.domain.User;
import com.home.banking.transaction.domain.ports.in.UserUseCase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAdapter implements UserUseCase {

    @Override
    public Optional<User> retrieveUserBy(Long userId) {
        return Optional.empty();
    }
}
