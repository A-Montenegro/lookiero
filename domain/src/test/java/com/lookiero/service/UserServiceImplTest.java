package com.lookiero.service;

import com.lookiero.data.StatisticDto;
import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;
import com.lookiero.ports.spi.StatisticPersistencePort;
import com.lookiero.ports.spi.UserPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private final static Long USER_ID = 1L;

    private final static String USER_NAME = "username";

    private final static String USER_PASSWORD = "password";

    private final static Integer USER_AGE = 20;

    private final static LocalDate USER_BIRTH_DATE = LocalDate.parse("1986-06-30", DateTimeFormatter.ISO_DATE);

    private final static BigDecimal USER_HEIGHT = new BigDecimal("1.71");

    private final static Integer USER_WEIGHT = 70;

    private final static Long STATISTIC_ID = 1L;

    private final static Integer STATISTIC_BIRTH_YEAR = 1986;

    private final static BigDecimal STATISTIC_AVERAGE_BMI = new BigDecimal("23.94");

    private final static UserDto DEFAULT_USER_DTO = new UserDto(USER_ID, USER_NAME, USER_AGE, USER_PASSWORD, USER_BIRTH_DATE,
            USER_HEIGHT, USER_WEIGHT);

    private final static UpdateUserDto DEFAULT_UPDATE_USER_DTO = new UpdateUserDto(USER_ID, USER_HEIGHT, USER_WEIGHT);

    private final static StatisticDto DEFAULT_STATISTIC_DTO = new StatisticDto(STATISTIC_ID, STATISTIC_BIRTH_YEAR, STATISTIC_AVERAGE_BMI);

    @Mock
    private StatisticPersistencePort statisticPersistencePort;

    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private UserServiceImpl userService;

    @Nested
    class Add {
        @Test
        void given_userWithYearWithExistingStatistic_when_callAdd_expect_callUserPortAddMethodAndStatisticPortUpdateMethod() {
            Mockito.when(UserServiceImplTest.this.userPersistencePort.add(DEFAULT_USER_DTO)).thenReturn(DEFAULT_USER_DTO);
            Mockito.when(UserServiceImplTest.this.userPersistencePort.findByBirthYear(USER_BIRTH_DATE.getYear()))
                    .thenReturn(List.of(DEFAULT_USER_DTO));
            Mockito.when(UserServiceImplTest.this.statisticPersistencePort.findByBirthYear(USER_BIRTH_DATE.getYear()))
                    .thenReturn(Optional.of(DEFAULT_STATISTIC_DTO));

            UserServiceImplTest.this.userService.add(DEFAULT_USER_DTO);

            Mockito.verify(UserServiceImplTest.this.userPersistencePort).add(DEFAULT_USER_DTO);
            Mockito.verify(UserServiceImplTest.this.userPersistencePort).findByBirthYear(USER_BIRTH_DATE.getYear());
            Mockito.verify(UserServiceImplTest.this.statisticPersistencePort).findByBirthYear(USER_BIRTH_DATE.getYear());
            Mockito.verify(UserServiceImplTest.this.statisticPersistencePort).update(DEFAULT_STATISTIC_DTO);
        }

        @Test
        void given_userWithYearWithoutExistingStatistic_when_callAdd_expect_callUserPortAddMethodAndStatisticPortAddMethod() {
            Mockito.when(UserServiceImplTest.this.userPersistencePort.add(DEFAULT_USER_DTO)).thenReturn(DEFAULT_USER_DTO);
            Mockito.when(UserServiceImplTest.this.userPersistencePort.findByBirthYear(USER_BIRTH_DATE.getYear()))
                    .thenReturn(List.of(DEFAULT_USER_DTO));
            Mockito.when(UserServiceImplTest.this.statisticPersistencePort.findByBirthYear(USER_BIRTH_DATE.getYear()))
                    .thenReturn(Optional.empty());

            UserServiceImplTest.this.userService.add(DEFAULT_USER_DTO);

            Mockito.verify(UserServiceImplTest.this.userPersistencePort).add(DEFAULT_USER_DTO);
            Mockito.verify(UserServiceImplTest.this.userPersistencePort).findByBirthYear(USER_BIRTH_DATE.getYear());
            Mockito.verify(UserServiceImplTest.this.statisticPersistencePort).findByBirthYear(USER_BIRTH_DATE.getYear());
            Mockito.verify(UserServiceImplTest.this.statisticPersistencePort)
                    .add(new StatisticDto(null, STATISTIC_BIRTH_YEAR, STATISTIC_AVERAGE_BMI));
        }

        @Test
        void given_userWithExistingUsername_when_callAdd_expect_illegalArgumentException() {
            Mockito.when(UserServiceImplTest.this.userPersistencePort.findByUsername(USER_NAME))
                    .thenReturn(Optional.of(DEFAULT_USER_DTO));

            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                UserServiceImplTest.this.userService.add(DEFAULT_USER_DTO);
            });
        }
    }

    @Nested
    class Update {
        @Test
        void given_userWithYearWithExistingStatistic_when_callUpdate_expect_callUserPortUpdateMethodAndStatisticPortUpdateMethod() {
            Mockito.when(UserServiceImplTest.this.userPersistencePort.findById(USER_ID))
                    .thenReturn(Optional.of(DEFAULT_USER_DTO));
            Mockito.when(UserServiceImplTest.this.userPersistencePort.update(DEFAULT_USER_DTO, DEFAULT_UPDATE_USER_DTO))
                    .thenReturn(DEFAULT_USER_DTO);
            Mockito.when(UserServiceImplTest.this.statisticPersistencePort.findByBirthYear(USER_BIRTH_DATE.getYear()))
                    .thenReturn(Optional.of(DEFAULT_STATISTIC_DTO));
            Mockito.when(UserServiceImplTest.this.userPersistencePort.findByBirthYear(USER_BIRTH_DATE.getYear()))
                    .thenReturn(List.of(DEFAULT_USER_DTO));

            UserServiceImplTest.this.userService.update(DEFAULT_UPDATE_USER_DTO);

            Mockito.verify(UserServiceImplTest.this.userPersistencePort).update(DEFAULT_USER_DTO, DEFAULT_UPDATE_USER_DTO);
            Mockito.verify(UserServiceImplTest.this.userPersistencePort).findByBirthYear(USER_BIRTH_DATE.getYear());
            Mockito.verify(UserServiceImplTest.this.statisticPersistencePort).findByBirthYear(USER_BIRTH_DATE.getYear());
            Mockito.verify(UserServiceImplTest.this.statisticPersistencePort).update(DEFAULT_STATISTIC_DTO);
        }

        @Test
        void given_userWithYearWithoutExistingStatistic_when_callUpdate_expect_callUserPortUpdateMethodAndStatisticPortUpdateMethod() {
            Mockito.when(UserServiceImplTest.this.userPersistencePort.findById(USER_ID))
                    .thenReturn(Optional.of(DEFAULT_USER_DTO));
            Mockito.when(UserServiceImplTest.this.userPersistencePort.update(DEFAULT_USER_DTO, DEFAULT_UPDATE_USER_DTO))
                    .thenReturn(DEFAULT_USER_DTO);
            Mockito.when(UserServiceImplTest.this.statisticPersistencePort.findByBirthYear(USER_BIRTH_DATE.getYear()))
                    .thenReturn(Optional.empty());
            Mockito.when(UserServiceImplTest.this.userPersistencePort.findByBirthYear(USER_BIRTH_DATE.getYear()))
                    .thenReturn(List.of(DEFAULT_USER_DTO));

            UserServiceImplTest.this.userService.update(DEFAULT_UPDATE_USER_DTO);

            Mockito.verify(UserServiceImplTest.this.userPersistencePort).update(DEFAULT_USER_DTO, DEFAULT_UPDATE_USER_DTO);
            Mockito.verify(UserServiceImplTest.this.userPersistencePort).findByBirthYear(USER_BIRTH_DATE.getYear());
            Mockito.verify(UserServiceImplTest.this.statisticPersistencePort).findByBirthYear(USER_BIRTH_DATE.getYear());
            Mockito.verify(UserServiceImplTest.this.statisticPersistencePort)
                    .add(new StatisticDto(null, STATISTIC_BIRTH_YEAR, STATISTIC_AVERAGE_BMI));
        }

        @Test
        void given_userWithNotExistingId_when_callUpdate_expect_illegalArgumentException() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                UserServiceImplTest.this.userService.update(DEFAULT_UPDATE_USER_DTO);
            });
        }
    }

    @Nested
    class FindAll {
        @Test
        void when_callFindAll_expect_callPortFindAllMethod() {
            UserServiceImplTest.this.userService.findAll();

            Mockito.verify(UserServiceImplTest.this.userPersistencePort).findAll();
        }
    }

}
