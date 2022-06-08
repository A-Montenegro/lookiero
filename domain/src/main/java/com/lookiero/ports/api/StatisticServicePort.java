package com.lookiero.ports.api;

import com.lookiero.data.StatisticDto;
import java.util.List;

public interface StatisticServicePort {
    List<StatisticDto> findAll();
}
