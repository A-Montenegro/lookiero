package com.lookiero.controller.statistic;

import com.lookiero.data.StatisticDto;
import com.lookiero.ports.api.StatisticServicePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StatisticControllerTest {

    private final static Long ID_STATISTIC_1 = 1L;

    private final static Long ID_STATISTIC_2 = 2L;

    private final static Integer YEAR_STATISTIC_1 = 1986;

    private final static Integer YEAR_STATISTIC_2 = 1989;

    private final static BigDecimal AVERAGE_BMI_STATISTIC_1 = new BigDecimal("25.98");

    private final static BigDecimal AVERAGE_BMI_STATISTIC_2 = new BigDecimal("27.14");

    @Mock
    private StatisticServicePort statisticServicePort;

    @InjectMocks
    private final StatisticController statisticController = new StatisticController();

    @Nested
    class FindAll {
        private final List<StatisticDto> DEFAULT_STATISTICS = getDefaultStatisticDtoList();

        @Test
        void when_atLeastOneStatisticExists_expect_returnStatisticsList() {
            Mockito.when(StatisticControllerTest.this.statisticServicePort.findAll()).thenReturn(DEFAULT_STATISTICS);

            final ResponseEntity<List<StatisticDto>> results = StatisticControllerTest.this.statisticController.findAll();

            Assertions.assertEquals(DEFAULT_STATISTICS, results.getBody());
        }

        @Test
        void when_noStatisticExists_expect_returnStatisticsList() {
            Mockito.when(StatisticControllerTest.this.statisticServicePort.findAll()).thenReturn(Collections.emptyList());

            final ResponseEntity<List<StatisticDto>> results = StatisticControllerTest.this.statisticController.findAll();

            Assertions.assertEquals(Collections.emptyList(), results.getBody());
        }
    }

    private List<StatisticDto> getDefaultStatisticDtoList() {
        final StatisticDto statisticDto1 = new StatisticDto(ID_STATISTIC_1, YEAR_STATISTIC_1, AVERAGE_BMI_STATISTIC_1);
        final StatisticDto statisticDto2 = new StatisticDto(ID_STATISTIC_2, YEAR_STATISTIC_2, AVERAGE_BMI_STATISTIC_2);
        return List.of(statisticDto1, statisticDto2);
    }
}
