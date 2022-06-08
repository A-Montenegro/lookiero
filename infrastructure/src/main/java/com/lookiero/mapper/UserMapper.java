package com.lookiero.mapper;

import com.lookiero.data.UpdateUserDto;
import com.lookiero.entity.User;
import com.lookiero.data.UserDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Mapper
public interface UserMapper {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(final User user);

    @Mapping(target = "password", expression = "java(bCryptPasswordEncoder.encode(userDto.password()))")
    User userDtoToUser(final UserDto userDto);

    void updateUser(@MappingTarget final User existingUser, final UpdateUserDto incomingUpdateUserDto);

    List<UserDto> userListToUserDtoList(final List<User> userList);
}
