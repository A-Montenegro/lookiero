package com.lookiero.mapper;

import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;
import com.lookiero.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserMapperTest {

    private final static Long USER_ID = 1L;

    private final static String USER_NAME = "username";

    private final static String USER_PASSWORD = "password";

    private final static Integer USER_AGE = 20;

    private final static LocalDate USER_BIRTH_DATE = LocalDate.parse("1986-06-30", DateTimeFormatter.ISO_DATE);

    private final static BigDecimal USER_HEIGHT = new BigDecimal("1.71");

    private final static Integer USER_WEIGHT = 70;

    private final static Long USER_DTO_ID = 2L;

    private final static String USER_DTO_NAME = "username2";

    private final static String USER_DTO_PASSWORD = "password2";

    private final static Integer USER_DTO_AGE = 40;

    private final static LocalDate USER_DTO_BIRTH_DATE = LocalDate.parse("1989-06-30", DateTimeFormatter.ISO_DATE);

    private final static BigDecimal USER_DTO_HEIGHT = new BigDecimal("1.91");

    private final static Integer USER_DTO_WEIGHT = 90;

    private final static User DEFAULT_USER = new User(USER_ID, USER_NAME, USER_AGE, USER_PASSWORD, USER_BIRTH_DATE,
            USER_HEIGHT, USER_WEIGHT);

    private final static UserDto DEFAULT_USER_DTO = new UserDto(USER_DTO_ID, USER_DTO_NAME, USER_DTO_AGE,
            USER_DTO_PASSWORD, USER_DTO_BIRTH_DATE, USER_DTO_HEIGHT, USER_DTO_WEIGHT);

    private final static UpdateUserDto DEFAULT_UPDATE_USER_DTO = new UpdateUserDto(USER_DTO_ID, USER_DTO_HEIGHT,
            USER_DTO_WEIGHT);

    private final UserMapper userMapper = new UserMapperImpl();

    @Nested
    class UserListToUserDtoList {
        @Test
        void expect_mappAllFiels() {
            List<UserDto> results = UserMapperTest.this.userMapper.userListToUserDtoList(List.of(DEFAULT_USER));

            Assertions.assertEquals(USER_ID, results.get(0).id());
            Assertions.assertEquals(USER_NAME, results.get(0).username());
            Assertions.assertEquals(USER_AGE, results.get(0).age());
            Assertions.assertEquals(USER_PASSWORD, results.get(0).password());
            Assertions.assertEquals(USER_HEIGHT, results.get(0).height());
            Assertions.assertEquals(USER_WEIGHT, results.get(0).weight());
        }
    }

    @Nested
    class UserDtoToUser {
        @Test
        void expect_mappAllFiels() {
            User result = UserMapperTest.this.userMapper.userDtoToUser(DEFAULT_USER_DTO);

            Assertions.assertEquals(USER_DTO_ID, result.getId());
            Assertions.assertEquals(USER_DTO_NAME, result.getUsername());
            Assertions.assertEquals(USER_DTO_AGE, result.getAge());
            Assertions.assertNotEquals(USER_DTO_PASSWORD, result.getPassword());
            Assertions.assertEquals(USER_DTO_HEIGHT, result.getHeight());
            Assertions.assertEquals(USER_DTO_WEIGHT, result.getWeight());
        }
    }

    @Nested
    class UpdateUser {
        @Test
        void expect_mappAllFiels() {
            User targetUser = DEFAULT_USER.toBuilder().build();

            UserMapperTest.this.userMapper.updateUser(targetUser, DEFAULT_UPDATE_USER_DTO);

            Assertions.assertEquals(USER_DTO_ID, targetUser.getId());
            Assertions.assertEquals(USER_NAME, targetUser.getUsername());
            Assertions.assertEquals(USER_AGE, targetUser.getAge());
            Assertions.assertEquals(USER_PASSWORD, targetUser.getPassword());
            Assertions.assertEquals(USER_DTO_HEIGHT, targetUser.getHeight());
            Assertions.assertEquals(USER_DTO_WEIGHT, targetUser.getWeight());
        }
    }

    @Nested
    class UserToUserDto {
        @Test
        void expect_mappAllFiels() {
            final UserDto result = UserMapperTest.this.userMapper.userToUserDto(DEFAULT_USER);

            Assertions.assertEquals(USER_ID, result.id());
            Assertions.assertEquals(USER_NAME, result.username());
            Assertions.assertEquals(USER_AGE, result.age());
            Assertions.assertEquals(USER_PASSWORD, result.password());
            Assertions.assertEquals(USER_HEIGHT, result.height());
            Assertions.assertEquals(USER_WEIGHT, result.weight());
        }
    }
}
