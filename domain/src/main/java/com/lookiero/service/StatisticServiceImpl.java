package com.lookiero.service;

import com.lookiero.data.StatisticDto;
import com.lookiero.ports.api.StatisticServicePort;
import com.lookiero.ports.spi.StatisticPersistencePort;

import java.util.List;

public class StatisticServiceImpl implements StatisticServicePort {

    private final StatisticPersistencePort statisticPersistencePort;

    public StatisticServiceImpl(final StatisticPersistencePort statisticPersistencePort) {
        this.statisticPersistencePort = statisticPersistencePort;
    }

    @Override
    public StatisticDto add(final StatisticDto statisticDto) {
        return statisticPersistencePort.add(statisticDto);
    }

    @Override
    public StatisticDto update(final StatisticDto statisticDto) {
        return statisticPersistencePort.update(statisticDto);
    }

    @Override
    public List<StatisticDto> findAll() {
        return statisticPersistencePort.findAll();
    }
    
}