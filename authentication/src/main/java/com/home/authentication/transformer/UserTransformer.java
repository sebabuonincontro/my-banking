package com.home.authentication.transformer;

import com.home.common.entities.Role;
import com.home.common.entities.dtos.SignUpDto;
import com.home.common.entities.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserTransformer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO from(SignUpDto dto) {
        return UserDTO.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .enabled(true)
                .role(Role.USER_ADMIN.getRole())
                .build();
    }

}
