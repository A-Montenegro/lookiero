package com.lookiero.commandlineinterface;

import com.lookiero.controller.statistic.StatisticCliController;
import com.lookiero.controller.user.UserCliController;
import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;
import com.lookiero.ports.api.StatisticServicePort;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;

@ExtendWith(MockitoExtension.class)
public class CommandLineInterfaceTest {

    private static final String ADD_USER_COMMAND = "addUser username 22 password 1986-02-10 1.71 65";

    private static final String UPDATE_USER_COMMAND = "updateUser 1 1.81 75";

    private static final String FIND_USERS_COMMAND = "findUsers";

    private static final String FIND_STATISTICS_COMMAND = "findStatistics";

    private static final String HELP_COMMAND = "help";

    private static final String UNKNOWN_COMMAND = "unknown command";

    @Mock
    private UserCliController userCliController;

    @Mock
    private StatisticCliController statisticCliController;

    @Mock
    private StatisticServicePort statisticServicePort;

    @InjectMocks
    private CommandLineInterface commandLineInterface;

    @Nested
    class Run {
        @Test
        void given_callRun_when_noInput_expect_noInteraction() {
            CommandLineInterfaceTest.this.commandLineInterface.run();

            Mockito.verifyNoInteractions(CommandLineInterfaceTest.this.userCliController);
            Mockito.verifyNoInteractions(CommandLineInterfaceTest.this.statisticCliController);
        }

        @Test
        void given_callRun_when_helpInput_expect_noInteraction() {
            System.setIn(new ByteArrayInputStream(HELP_COMMAND.getBytes()));
            CommandLineInterfaceTest.this.commandLineInterface.run();

            Mockito.verifyNoInteractions(CommandLineInterfaceTest.this.userCliController);
            Mockito.verifyNoInteractions(CommandLineInterfaceTest.this.statisticCliController);
        }

        @Test
        void given_callRun_when_unknownInput_expect_noInteraction() {
            System.setIn(new ByteArrayInputStream(UNKNOWN_COMMAND.getBytes()));
            CommandLineInterfaceTest.this.commandLineInterface.run();

            Mockito.verifyNoInteractions(CommandLineInterfaceTest.this.userCliController);
            Mockito.verifyNoInteractions(CommandLineInterfaceTest.this.statisticCliController);
        }

        @Test
        void given_callRun_when_addUserInput_expect_callUserCliControllerAdd() {
            System.setIn(new ByteArrayInputStream(ADD_USER_COMMAND.getBytes()));
            CommandLineInterfaceTest.this.commandLineInterface.run();

            Mockito.verify(CommandLineInterfaceTest.this.userCliController).add(Mockito.any(UserDto.class));
            Mockito.verifyNoInteractions(CommandLineInterfaceTest.this.statisticCliController);
        }

        @Test
        void given_callRun_when_updateUserInput_expect_callUserCliControllerAdd() {
            System.setIn(new ByteArrayInputStream(UPDATE_USER_COMMAND.getBytes()));
            CommandLineInterfaceTest.this.commandLineInterface.run();

            Mockito.verify(CommandLineInterfaceTest.this.userCliController).update(Mockito.any(UpdateUserDto.class));
            Mockito.verifyNoInteractions(CommandLineInterfaceTest.this.statisticCliController);
        }

        @Test
        void given_callRun_when_findUsersInput_expect_callUserCliControllerFindAll() {
            System.setIn(new ByteArrayInputStream(FIND_USERS_COMMAND.getBytes()));
            CommandLineInterfaceTest.this.commandLineInterface.run();

            Mockito.verify(CommandLineInterfaceTest.this.userCliController).findAll();
            Mockito.verifyNoInteractions(CommandLineInterfaceTest.this.statisticCliController);
        }

        @Test
        void given_callRun_when_findStatisticsInput_expect_callStatisticCliControllerFindAll() {
            System.setIn(new ByteArrayInputStream(FIND_STATISTICS_COMMAND.getBytes()));
            CommandLineInterfaceTest.this.commandLineInterface.run();

            Mockito.verify(CommandLineInterfaceTest.this.statisticCliController).findAll();
            Mockito.verifyNoInteractions(CommandLineInterfaceTest.this.userCliController);
        }
    }

}
