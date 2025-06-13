package com.home.authentication.controllers;

import com.home.authentication.services.AuthService;
import com.home.common.entities.dtos.SignInDto;
import com.home.common.entities.dtos.SignUpDto;
import com.home.common.entities.dtos.TokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public TokenDto signUp(@RequestBody SignUpDto userDto) {
        return authService.signUp(userDto);
    }

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TokenDto signIn(@RequestBody SignInDto userDto) {
        return authService.signIn(userDto);
    }
}
