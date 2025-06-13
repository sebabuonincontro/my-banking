package com.home.common.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  @NotBlank
  private String email;

  private String token;

}
