package com.lookiero.ports.spi;

import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {

    UserDto add(final UserDto userDto);

    UserDto update(final UserDto existingUserDto, final UpdateUserDto incomingUserDto);

    Optional<UserDto> findById(final Long id);

    Optional<UserDto> findByUsername(final String username);

    List<UserDto> findAll();

    List<UserDto> findByBirthYear(final Integer birthYear);

}
