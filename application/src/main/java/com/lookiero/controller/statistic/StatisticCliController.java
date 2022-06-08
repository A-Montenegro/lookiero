package com.lookiero.controller.statistic;

import com.lookiero.data.StatisticDto;
import com.lookiero.ports.api.StatisticServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public class StatisticCliController {

    @Autowired
    private StatisticServicePort statisticServicePort;

    public List<StatisticDto> findAll() {
        return statisticServicePort.findAll();
    }

}