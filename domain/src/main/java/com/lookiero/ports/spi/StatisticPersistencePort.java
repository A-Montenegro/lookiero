package com.lookiero.ports.spi;

import com.lookiero.data.StatisticDto;

import java.util.List;
import java.util.Optional;

public interface StatisticPersistencePort {

    StatisticDto add(final StatisticDto statisticDto);

    StatisticDto update(final StatisticDto statisticDto);

    List<StatisticDto> findAll();

    Optional<StatisticDto> findByBirthYear(final Integer year);

}
