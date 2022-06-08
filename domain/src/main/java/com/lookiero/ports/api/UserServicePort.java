package com.lookiero.ports.api;

import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;
import java.util.List;

public interface UserServicePort {

    UserDto add(final UserDto incomingUserDto);

    UserDto update(final UpdateUserDto incomingUpdateUserDto);

    List<UserDto> findAll();

    List<UserDto> findByYear(final Integer year);
}
