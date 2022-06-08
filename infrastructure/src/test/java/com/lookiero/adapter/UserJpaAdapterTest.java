package com.lookiero.service;

import com.lookiero.adapter.UserJpaAdapter;
import com.lookiero.data.StatisticDto;
import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;
import com.lookiero.entity.User;
import com.lookiero.ports.spi.StatisticPersistencePort;
import com.lookiero.repository.UserRepository;
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

@ExtendWith(MockitoExtension.class)
public class UserJpaAdapterTest {


    private final static Long USER_ID = 1L;

    private final static String USER_NAME = "username";

    private final static String USER_PASSWORD = "password";

    private final static Integer USER_AGE = 20;

    private final static LocalDate USER_BIRTH_DATE = LocalDate.parse("1986-06-30", DateTimeFormatter.ISO_DATE);

    private final static BigDecimal USER_HEIGHT = new BigDecimal("1.71");

    private final static Integer USER_WEIGHT = 70;

    private final static UserDto DEFAULT_USER_DTO = new UserDto(USER_ID, USER_NAME, USER_AGE, USER_PASSWORD, USER_BIRTH_DATE,
            USER_HEIGHT, USER_WEIGHT);

    private final static UpdateUserDto DEFAULT_UPDATE_USER_DTO = new UpdateUserDto(USER_ID, USER_HEIGHT, USER_WEIGHT);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @Nested
    class Add {
        @Test
        void when_callAdd_expect_callRepoSaveMethod() {
            UserJpaAdapterTest.this.userJpaAdapter.add(DEFAULT_USER_DTO);

            Mockito.verify(UserJpaAdapterTest.this.userRepository).save(Mockito.any(User.class));
        }
    }

    @Nested
    class Update {
        @Test
        void when_callUpdate_expect_callRepoSaveMethod() {
            UserJpaAdapterTest.this.userJpaAdapter.update(DEFAULT_USER_DTO, DEFAULT_UPDATE_USER_DTO);

            Mockito.verify(UserJpaAdapterTest.this.userRepository).save(Mockito.any(User.class));
        }
    }

    @Nested
    class FindById {
        @Test
        void when_callFindById_expect_callRepoFindByIdMethod() {
            UserJpaAdapterTest.this.userJpaAdapter.findById(USER_ID);

            Mockito.verify(UserJpaAdapterTest.this.userRepository).findById(USER_ID);
        }
    }

    @Nested
    class FindByUsername {
        @Test
        void when_callFindAll_expect_callRepoFindAllMethod() {
            UserJpaAdapterTest.this.userJpaAdapter.findByUsername(USER_NAME);

            Mockito.verify(UserJpaAdapterTest.this.userRepository).findByUsername(USER_NAME);
        }
    }

    @Nested
    class FindByBirthYear {
        @Test
        void when_callFindByBirthYear_expect_callRepoFindByBirthDateBetweenMethod() {
            final LocalDate expectedDateStart = LocalDate.parse("1986-01-01", DateTimeFormatter.ISO_DATE);
            final LocalDate expectedDateEnd = LocalDate.parse("1986-12-31", DateTimeFormatter.ISO_DATE);
            UserJpaAdapterTest.this.userJpaAdapter.findByBirthYear(USER_BIRTH_DATE.getYear());

            Mockito.verify(UserJpaAdapterTest.this.userRepository)
                    .findByBirthDateBetween(expectedDateStart, expectedDateEnd);
        }
    }

}
