package com.lookiero.adapter;

import com.lookiero.data.StatisticDto;
import com.lookiero.entity.Statistic;
import com.lookiero.repository.StatisticRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class StatisticJpaAdapterTest {

    private final static Long STATISTIC_ID = 1L;

    private final static Integer STATISTIC_BIRTH_YEAR = 1986;

    private final static BigDecimal AVERAGE_BMI = new BigDecimal("22.97");

    private final static StatisticDto DEFAULT_STATISTIC_DTO =
            new StatisticDto(STATISTIC_ID, STATISTIC_BIRTH_YEAR, AVERAGE_BMI);

    @Mock
    private StatisticRepository statisticRepository;

    @InjectMocks
    private StatisticJpaAdapter statisticJpaAdapter;

    @Nested
    class Add {
        @Test
        void when_callAdd_expect_callRepoSaveMethod() {
            StatisticJpaAdapterTest.this.statisticJpaAdapter.add(DEFAULT_STATISTIC_DTO);

            Mockito.verify(StatisticJpaAdapterTest.this.statisticRepository).save(Mockito.any(Statistic.class));
        }
    }

    @Nested
    class Update {
        @Test
        void when_callUpdate_expect_callRepoSaveMethod() {
            StatisticJpaAdapterTest.this.statisticJpaAdapter.update(DEFAULT_STATISTIC_DTO);

            Mockito.verify(StatisticJpaAdapterTest.this.statisticRepository).save(Mockito.any(Statistic.class));
        }
    }

    @Nested
    class FindByBirthYear {
        @Test
        void when_callFindByBirthYear_expect_callRepoFindByBirthYearMethod() {
            StatisticJpaAdapterTest.this.statisticJpaAdapter.findByBirthYear(DEFAULT_STATISTIC_DTO.birthYear());

            Mockito.verify(StatisticJpaAdapterTest.this.statisticRepository).findByBirthYear(DEFAULT_STATISTIC_DTO.birthYear());
        }
    }

    @Nested
    class FindAll {
        @Test
        void when_callFindAll_expect_callRepoFindAllMethod() {
            StatisticJpaAdapterTest.this.statisticJpaAdapter.findAll();

            Mockito.verify(StatisticJpaAdapterTest.this.statisticRepository).findAllByOrderByAverageBmi();
        }
    }
}
