package com.lookiero.service;

import com.lookiero.ports.spi.StatisticPersistencePort;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatisticServiceImplTest {
    @Mock
    private StatisticPersistencePort statisticPersistencePort;

    @InjectMocks
    private StatisticServiceImpl statisticService;

    @Nested
    class FindAll {

        @Test
        void when_callFindAll_expect_callPortFindAllMethod() {
            StatisticServiceImplTest.this.statisticService.findAll();

            Mockito.verify(StatisticServiceImplTest.this.statisticPersistencePort).findAll();
        }
    }

}
