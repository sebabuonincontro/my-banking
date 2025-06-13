package com.home.authentication.services;

import com.google.common.collect.Maps;
import com.home.authentication.exceptions.AuthException;
import com.home.authentication.transformer.UserTransformer;
import com.home.common.entities.dtos.SignInDto;
import com.home.common.entities.dtos.SignUpDto;
import com.home.common.entities.dtos.TokenDto;
import com.home.common.rest.client.UserClient;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

  private final UserClient userClient;
  private final UserTransformer userTransformer;
  private final JwtTokenService jwtTokenService;

  public AuthService(UserClient userClient, UserTransformer userTransformer, JwtTokenService jwtTokenService) {
    this.userClient = userClient;
    this.userTransformer = userTransformer;
    this.jwtTokenService = jwtTokenService;
  }

  public TokenDto signUp(SignUpDto userDto) {
    val user = userClient.retrieveUserBy(userDto.getUsername());
    if(user.isPresent()){
      throw new AuthException(userDto.getUsername(), "username already exist");
    } else {
      return Optional.of(userDto)
        .map(userTransformer::from)
        .map(userClient::save)
        .map(dto -> {
          val token = jwtTokenService.createToken(dto.getUsername(), Maps.newHashMap());
          return TokenDto.builder().withToken(token).build();
        })
        .orElseThrow(() -> new AuthException(userDto.getUsername(), "auth unexpected error."));
    }
  }

  public TokenDto signIn(SignInDto userDto) {
    return userClient.retrieveUserBy(userDto.getUsername())
      .filter(expected -> expected.getUsername().equals(userDto.getUsername()))
      .map(expected -> {
          val token = jwtTokenService.generateToken(userDto.getUsername());
          return TokenDto.builder().withToken(token).build();
      })
      .orElseThrow(() -> new AuthException(userDto.getUsername(), "invalid username or password"));
  }

}
