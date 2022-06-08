package com.lookiero.controller.user;

import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;
import com.lookiero.exception.ExceptionHandledEntryPoint;
import com.lookiero.ports.api.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends ExceptionHandledEntryPoint {

    @Autowired
    private UserServicePort userServicePort;

    @PostMapping(value = "/add", consumes = { "application/json" }, produces = { "application/json" })
    public UserDto add(@Valid @RequestBody final UserDto incomingUserDto) {
        return userServicePort.add(incomingUserDto);
    }

    @PutMapping(value = "/update", consumes = { "application/json" }, produces = { "application/json" })
    public UserDto update(@Valid @RequestBody final UpdateUserDto incomingUpdateUserDto) {
        return userServicePort.update(incomingUpdateUserDto);
    }

    @GetMapping(value = "/findAll", produces = { "application/json" })
    public ResponseEntity<List<UserDto>> findAll() {
        final List<UserDto> userDtoList = userServicePort.findAll();
        return ResponseEntity.ok(userDtoList);
    }

}
