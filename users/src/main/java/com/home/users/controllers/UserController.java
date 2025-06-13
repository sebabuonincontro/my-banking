package com.home.users.controllers;

import com.home.common.entities.dtos.UserDTO;
import com.home.users.exceptions.UserException;
import com.home.users.services.UserEvent;
import com.home.users.services.UserEventManager;
import com.home.users.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserEventManager userEventManager;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDTO userDTO) {
        Optional.ofNullable(userDTO)
            .map(dto -> UserEvent.builder()
                .message("user")
                .status("OK")
                .user(userDTO)
                .build())
            .ifPresentOrElse(userEventManager::sendMessage, () -> { throw new UserException("Error while persist the following user " + userDTO);});
    }

    @GetMapping("/{username}")
    public UserDTO getById(@PathVariable("username") String username) {
        return userService.getByUsername(username);
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAllUsers();
    }

}