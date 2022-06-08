package com.lookiero.service;

import com.lookiero.data.StatisticDto;
import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;
import com.lookiero.ports.api.UserServicePort;
import com.lookiero.ports.spi.StatisticPersistencePort;
import com.lookiero.ports.spi.UserPersistencePort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserServicePort {

    private final UserPersistencePort userPersistencePort;

    private final StatisticPersistencePort statisticPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String EXISTING_USER_ERROR = "El usuario indicado ya existe.";

    private static final String INVALID_IDENTIFIER = "No existe un usuario con el identificador indicado.";

    public UserServiceImpl(final UserPersistencePort userPersistencePort,
                           final StatisticPersistencePort statisticPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.statisticPersistencePort = statisticPersistencePort;
    }

    @Override
    public UserDto add(final UserDto incomingUserDto) {
        if(this.userPersistencePort.findByUsername(incomingUserDto.username()).isPresent()) {
            logger.error(EXISTING_USER_ERROR);
            throw new IllegalArgumentException(EXISTING_USER_ERROR);
        }
        final UserDto createdUserDto = this.userPersistencePort.add(incomingUserDto);
        this.updateStatistic(createdUserDto.birthDate().getYear());
        return createdUserDto;
    }

    @Override
    public UserDto update(final UpdateUserDto incomingUpdateUserDto) {
        final Optional<UserDto> existingUserDto = this.userPersistencePort.findById(incomingUpdateUserDto.id());
        if(existingUserDto.isEmpty()) {
            logger.error(INVALID_IDENTIFIER);
            throw new IllegalArgumentException(INVALID_IDENTIFIER);
        }
        final UserDto updatedUserDto = this.userPersistencePort.update(existingUserDto.get(), incomingUpdateUserDto);
        this.updateStatistic(updatedUserDto.birthDate().getYear());
        return updatedUserDto;
    }

    @Override
    public List<UserDto> findAll() {
        return this.userPersistencePort.findAll();
    }

    private void updateStatistic(final Integer birthYear) {
        final List<UserDto> usersByYear = this.userPersistencePort.findByBirthYear(birthYear);
        final BigDecimal averageBmi = this.getAverageBmi(usersByYear);
        final Optional<StatisticDto> statisticDto = this.statisticPersistencePort.findByBirthYear(birthYear);

        if(statisticDto.isPresent()) {
            final StatisticDto targetStatisticDto = new StatisticDto(statisticDto.get().id() , birthYear, averageBmi);
            this.statisticPersistencePort.update(targetStatisticDto);
        } else {
            final StatisticDto newStatisticDto = new StatisticDto(null , birthYear, averageBmi);
            this.statisticPersistencePort.add(newStatisticDto);
        }
    }

    private BigDecimal getAverageBmi(final List<UserDto> usersByYear) {
        return usersByYear.stream()
            .map(UserDto::getBmi)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .divide(BigDecimal.valueOf(usersByYear.size()), 2, RoundingMode.HALF_UP);
    }
}
