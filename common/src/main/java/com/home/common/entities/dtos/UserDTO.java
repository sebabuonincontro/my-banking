package com.home.common.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Long id;
  @NotBlank
  private String username;
  @NotBlank
  private String password;
  @NotBlank
  private String email;
  private Boolean enabled;
  private String role;

}
