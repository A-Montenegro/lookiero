package com.lookiero.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
public class UserDtoTest {

    private final static Long USER_ID = 1L;

    private final static String USER_NAME = "username";

    private final static String USER_PASSWORD = "password";

    private final static Integer USER_AGE = 20;

    private final static LocalDate USER_BIRTH_DATE = LocalDate.parse("1986-06-30", DateTimeFormatter.ISO_DATE);

    private final static BigDecimal USER_HEIGHT = new BigDecimal("1.71");

    private final static Integer USER_WEIGHT = 70;

    private final static UserDto USER_DTO_1 = new UserDto(USER_ID, USER_NAME, USER_AGE, USER_PASSWORD, USER_BIRTH_DATE,
            USER_HEIGHT, USER_WEIGHT);

    private final static UserDto USER_DTO_2 = new UserDto(USER_ID, USER_NAME, USER_AGE, USER_PASSWORD, USER_BIRTH_DATE,
            USER_HEIGHT.add(new BigDecimal("0.15")), USER_WEIGHT + 20);

    @Nested
    class GetBmi {
        @Test
        void when_givenUserDto_whenCallGetBmi_expect_returnCorrectBmi() {
            final BigDecimal expectedBmiUserDto1 = new BigDecimal("23.94");
            final BigDecimal expectedBmiUserDto2 = new BigDecimal("26.01");
            final BigDecimal result1 = USER_DTO_1.getBmi();
            final BigDecimal result2 = USER_DTO_2.getBmi();

            Assertions.assertEquals(expectedBmiUserDto1, result1);
            Assertions.assertEquals(expectedBmiUserDto2, result2);
        }
    }
}
