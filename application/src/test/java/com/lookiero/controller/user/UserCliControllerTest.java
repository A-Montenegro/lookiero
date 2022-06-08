package com.lookiero.controller.user;

import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;
import com.lookiero.ports.api.UserServicePort;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class UserCliControllerTest {

    private final static Long USER_ID = 2L;

    private final static String USER_NAME = "username2";

    private final static String USER_PASSWORD = "password2";

    private final static Integer USER_AGE = 22;

    private final static LocalDate USER_BIRTH_DATE = LocalDate.parse("1999-06-10", DateTimeFormatter.ISO_DATE);

    private final static BigDecimal USER_HEIGHT = new BigDecimal("1.81");

    private final static Integer USER_WEIGHT = 80;

    private final static UserDto DEFAULT_USER_DTO = new UserDto(USER_ID, USER_NAME, USER_AGE, USER_PASSWORD, USER_BIRTH_DATE,
            USER_HEIGHT, USER_WEIGHT);

    private final static UpdateUserDto DEFAULT_UPDATE_USER_DTO = new UpdateUserDto(USER_ID, USER_HEIGHT, USER_WEIGHT);


    @Mock
    private UserServicePort userServicePort;

    @InjectMocks
    private final UserCliController userCliController = new UserCliController();

    @Nested
    class Add {

        @Test
        void given_newUserDto_when_add_expect_callPortAddMethod() {
            UserCliControllerTest.this.userCliController.add(DEFAULT_USER_DTO);

            Mockito.verify(UserCliControllerTest.this.userServicePort).add(DEFAULT_USER_DTO);
        }
    }

    @Nested
    class Update {

        @Test
        void given_updateUserDtoTo_when_update_expect_callPortUpdateMethod() {
            UserCliControllerTest.this.userCliController.update(DEFAULT_UPDATE_USER_DTO);

            Mockito.verify(UserCliControllerTest.this.userServicePort).update(DEFAULT_UPDATE_USER_DTO);
        }
    }

    @Nested
    class FindAll {

        @Test
        void when_atLeastOneUserExists_expect_returnUsersList() {
            Mockito.when(UserCliControllerTest.this.userServicePort.findAll()).thenReturn(List.of(DEFAULT_USER_DTO));

            final List<UserDto> results = UserCliControllerTest.this.userCliController.findAll();

            Assertions.assertEquals(DEFAULT_USER_DTO, Objects.requireNonNull(results).get(0));
        }

        @Test
        void when_noUserExists_expect_returnUsersList() {
            Mockito.when(UserCliControllerTest.this.userServicePort.findAll()).thenReturn(Collections.emptyList());

            final List<UserDto> results = UserCliControllerTest.this.userCliController.findAll();

            Assertions.assertEquals(Collections.emptyList(), results);
        }
    }

}
