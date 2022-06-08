package com.lookiero.controller.user;

import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;
import com.lookiero.ports.api.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public class UserCliController {

    @Autowired
    private UserServicePort userServicePort;

    public void add(@Valid final UserDto incomingUserDto) {
        this.userServicePort.add(incomingUserDto);
    }

    public void update(@Valid final UpdateUserDto incomingUpdateUserDto) {
        this.userServicePort.update(incomingUpdateUserDto);
    }

    public List<UserDto> findAll() {
        return userServicePort.findAll();
    }

}
