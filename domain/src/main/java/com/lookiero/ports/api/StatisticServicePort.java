package com.lookiero.ports.api;

import com.lookiero.data.StatisticDto;
import java.util.List;

public interface StatisticServicePort {

    StatisticDto add(final StatisticDto statisticDto);

    StatisticDto update(final StatisticDto statisticDto);

    List<StatisticDto> findAll();
}
