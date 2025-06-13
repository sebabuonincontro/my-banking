package com.home.users.services;

import com.google.common.collect.Lists;
import com.home.common.entities.dtos.UserDTO;
import com.home.users.exceptions.UserException;
import com.home.users.exceptions.UserNotFoundException;
import com.home.users.mappers.UsersMapper;
import com.home.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(rollbackFor = HibernateException.class)
    public void create(UserDTO userDTO) {
        Optional.ofNullable(userDTO)
            .map(UsersMapper.INSTANCE::mapTo)
            .ifPresentOrElse(userRepository::save, UserException::new);
    }

    @Transactional(readOnly = true)
    public UserDTO getByUsername(String username) {
        try {
            return CompletableFuture
                .supplyAsync(() -> userRepository.findByUsername(username))
                .thenApply(optional -> optional.map(UsersMapper.INSTANCE::mapToDto)
                        .getOrElseThrow(() -> new UserNotFoundException(username)))
                .get(6000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException | ExecutionException | InterruptedException e) {
            throw new UserException("Error trying to get user with username: " + username, e);
        }
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return Lists.newArrayList(userRepository.findAll())
            .parallelStream()
            .map(UsersMapper.INSTANCE::mapToDto)
            .sorted(Comparator.comparing(UserDTO::getId))
            .collect(Collectors.toList());
    }

}
