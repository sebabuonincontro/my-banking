package com.home.users.mappers;

import com.home.common.entities.Role;
import com.home.common.entities.User;
import com.home.common.entities.dtos.UserDTO;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersMapperTest {


    public void testMapToDto() {
        val user = User.builder()
                .withId(1L)
                .withUsername("user")
                .withPassword("pass")
                .withEmail("email@email.com")
                .withEnabled(true)
                .withRole(Role.USER_ADMIN.getRole())
                .build();

        UserDTO dto = UsersMapper.INSTANCE.mapToDto(user);

        assertThat(dto.getId()).isEqualTo(user.getId());
        assertThat(dto.getUsername()).isEqualTo(user.getUsername());
        assertEquals(dto.getPassword(), user.getPassword());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getEnabled(), user.getEnabled());
        assertEquals(dto.getRole(), user.getRole());
    }


    public void testMapToEntity() {
        val dto = UserDTO.builder()
                .withId(1L)
                .withUsername("user")
                .withPassword("pass")
                .withEmail("email@email.com")
                .withEnabled(true)
                .withRole(Role.USER_ADMIN.getRole())
                .build();

        User user = UsersMapper.INSTANCE.mapTo(dto);

        assertEquals(dto.getId(), user.getId());
        assertEquals(dto.getUsername(), user.getUsername());
        assertEquals(dto.getPassword(), user.getPassword());
        assertEquals(dto.getEmail(), user.getEmail());
        assertEquals(dto.getEnabled(), user.getEnabled());
        assertEquals(dto.getRole(), user.getRole());
    }
}
