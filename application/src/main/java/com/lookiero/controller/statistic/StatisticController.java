package com.lookiero.controller.statistic;

import com.lookiero.data.StatisticDto;
import com.lookiero.exception.ExceptionHandledEntryPoint;
import com.lookiero.ports.api.StatisticServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController extends ExceptionHandledEntryPoint {

    @Autowired
    private StatisticServicePort statisticServicePort;

    @GetMapping(value = "/findAll", produces = { "application/json" })
    public ResponseEntity<List<StatisticDto>> findAll() {
        final List<StatisticDto> statisticDtoList = statisticServicePort.findAll();
        return ResponseEntity.ok(statisticDtoList);
    }

}