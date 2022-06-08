package com.lookiero.adapter;

import com.lookiero.data.UpdateUserDto;
import com.lookiero.entity.User;
import com.lookiero.mapper.UserMapper;
import com.lookiero.repository.UserRepository;
import com.lookiero.data.UserDto;
import com.lookiero.ports.spi.UserPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class UserJpaAdapter implements UserPersistencePort {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto add(final UserDto userDto) {
        final User user = UserMapper.INSTANCE.userDtoToUser(userDto);

        final User savedUser = this.userRepository.save(user);

        return UserMapper.INSTANCE.userToUserDto(savedUser);
    }

    @Override
    public UserDto update(final UserDto existingUserDto, final UpdateUserDto incomingUpdateUserDto) {
        final User existingUser = UserMapper.INSTANCE.userDtoToUser(existingUserDto);
        UserMapper.INSTANCE.updateUser(existingUser, incomingUpdateUserDto);
        final User updatedUser = this.userRepository.save(existingUser);

        return UserMapper.INSTANCE.userToUserDto(updatedUser);
    }

    @Override
    public Optional<UserDto> findById(final Long id) {
        final Optional<User> user = this.userRepository.findById(id);

        return user.map(UserMapper.INSTANCE::userToUserDto);
    }

    @Override
    public Optional<UserDto> findByUsername(final String username) {
        final Optional<User> user = this.userRepository.findByUsername(username);

        return user.map(UserMapper.INSTANCE::userToUserDto);
    }

    @Override
    public List<UserDto> findAll() {

        final List<User> userList = this.userRepository.findAll();

        return UserMapper.INSTANCE.userListToUserDtoList(userList);
    }

    @Override
    public List<UserDto> findByBirthYear(final Integer birthYear) {
        final int firstDayOfYear = 1;
        final LocalDate dateStart = LocalDate.ofYearDay(birthYear, firstDayOfYear);
        final LocalDate dateEnd = dateStart.with(TemporalAdjusters.lastDayOfYear());

        final List<User> userList = this.userRepository.findByBirthDateBetween(dateStart, dateEnd);

        return UserMapper.INSTANCE.userListToUserDtoList(userList);
    }
}
