package com.home.users.mappers;

import com.home.common.entities.User;
import com.home.common.entities.dtos.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    UserDTO mapToDto(User user);

    User mapTo(UserDTO userDTO);
}
