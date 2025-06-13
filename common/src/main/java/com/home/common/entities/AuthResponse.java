package com.home.common.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

  Integer code;
  String message;
  String description;

}
