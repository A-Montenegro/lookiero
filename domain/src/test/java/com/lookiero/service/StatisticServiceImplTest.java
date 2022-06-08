package com.lookiero.service;

import com.lookiero.data.StatisticDto;
import com.lookiero.ports.spi.StatisticPersistencePort;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceImplTest {

    private final static Long STATISTIC_ID = 1L;

    private final static Integer STATISTIC_BIRTH_YEAR = 1986;

    private final static BigDecimal AVERAGE_BMI = new BigDecimal("22.97");

    private final static StatisticDto DEFAULT_STATISTIC_DTO = new StatisticDto(STATISTIC_ID, STATISTIC_BIRTH_YEAR, AVERAGE_BMI);

    @Mock
    private StatisticPersistencePort statisticPersistencePort;

    @InjectMocks
    private StatisticServiceImpl statisticService;

    @Nested
    class Add {
        @Test
        void when_callAdd_expect_callPortAddMethod() {
            StatisticServiceImplTest.this.statisticService.add(DEFAULT_STATISTIC_DTO);

            Mockito.verify(StatisticServiceImplTest.this.statisticPersistencePort).add(DEFAULT_STATISTIC_DTO);
        }
    }

    @Nested
    class Update {
        @Test
        void when_callUpdate_expect_callPortUpdateMethod() {
            StatisticServiceImplTest.this.statisticService.update(DEFAULT_STATISTIC_DTO);

            Mockito.verify(StatisticServiceImplTest.this.statisticPersistencePort).update(DEFAULT_STATISTIC_DTO);
        }
    }

    @Nested
    class FindAll {

        @Test
        void when_callFindAll_expect_callPortFindAllMethod() {
            StatisticServiceImplTest.this.statisticService.findAll();

            Mockito.verify(StatisticServiceImplTest.this.statisticPersistencePort).findAll();
        }
    }

}
