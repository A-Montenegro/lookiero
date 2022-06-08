package com.lookiero.commandlineinterface;

import com.lookiero.controller.statistic.StatisticCliController;
import com.lookiero.controller.user.UserCliController;
import com.lookiero.data.StatisticDto;
import com.lookiero.data.UpdateUserDto;
import com.lookiero.data.UserDto;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CommandLineInterface implements CommandLineRunner {

    private final UserCliController userCliController;

    private final StatisticCliController statisticCliController;

    private static final Logger logger = LoggerFactory.getLogger(CommandLineInterface.class);

    private static final String WELLCOME_MESSAGE = """
            
            BIENVENIDO A LA INTERFAZ DE L\u00cdNEA DE COMANDOS DE LA APLICACI\u00d3N USERS MANAGER.
            
            PARA SABER C\u00d3MO INTERACTUAR CON LA APLICACI\u00d3N A UTILIZANDO COMANDOS, ESCRIBA EL COMANDO "help".
            """;

    private static final String USAGE_MESSAGE = """

            Comandos permitidos:
            
            addUser [username] [age] [password] [birthDate] [height] [weight]
            Descripci\u00f3n : Crea el usuario a partir de la informaci\u00f3n introducida.
            Ejemplo: "addUser username 20 mipassword 1986-01-31 1.85 90"

            updateUser [id] [height] [weight]
            Descripci\u00f3n : Actualiza la estatura y el peso del usuario a partir de la informaci\u00f3n introducida.
            Ejemplo: "updateUser 1 1.65 70"
            
            findUsers
            Descripci\u00f3n : Devuelve la lista de usuarios.
            Ejemplo: "findUsers"
            
            findStatistics
            Descripci\u00f3n : Devuelve la lista de estadisticas ordenadas por BMI.
            Ejemplo: "findStatistics"
            
            exit
            Descripci\u00f3n: Salir de la aplicaci\u00f3n
            Ejemplo: "exit"
            
            """;

    private static final String INPUT_ERROR_MESSAGE = "Los argumentos introducidos no son v\u00e1lidos.\n";

    public CommandLineInterface(final UserCliController userCliController,
                                final StatisticCliController statisticCliController) {
        this.userCliController = userCliController;
        this.statisticCliController = statisticCliController;
    }

    @Override
    public void run(String... args) {
        logger.info(WELLCOME_MESSAGE);
        final Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            final String line = scanner.nextLine();
            final String[] command = line.split(" ");
            this.performCommandAction(command);
        }
    }

    private void performCommandAction(final String... command) {
        try {
            switch (command[0]) {
                case "addUser" -> {
                    final UserDto incomingUserDto = new UserDto(null, command[1], Integer.parseInt(command[2]), command[3],
                            LocalDate.parse(command[4], DateTimeFormatter.ofPattern("yyyy-MM-dd")), new BigDecimal(command[5]),
                            Integer.parseInt(command[6]));
                    this.userCliController.add(incomingUserDto);
                    logger.info("\nUSUARIO CREADO CORRECTAMENTE\n");
                }
                case "updateUser" -> {
                    final UpdateUserDto incomingUpdateUserDto = new UpdateUserDto(Long.parseLong(command[1]),
                            new BigDecimal(command[2]), Integer.parseInt(command[3]));
                    this.userCliController.update(incomingUpdateUserDto);
                    logger.info("\nUSUARIO ACTUALIZADO CORRECTAMENTE\n");
                }
                case "findUsers" -> {
                    final List<UserDto> userDtoList = this.userCliController.findAll();
                    final String userDtoListPrettyString = this.toPrettyString(userDtoList);
                    logger.info("\nLISTA DE USUARIOS:\n{}", userDtoListPrettyString);
                }
                case "findStatistics" -> {
                    final List<StatisticDto> statisticDtoList = this.statisticCliController.findAll();
                    final String statisticDtoListPrettyString = this.toPrettyString(statisticDtoList);
                    logger.info("\nLISTA DE STATISTICS:\n{}", statisticDtoListPrettyString);
                }
                case "help" -> logger.info(USAGE_MESSAGE);
                case "exit" -> System.exit(0);
                default -> logger.info(INPUT_ERROR_MESSAGE);
            }
        } catch (final ConstraintViolationException exception) {
            logger.error(exception.getMessage());
        } catch (final Exception exception) {
            logger.info(INPUT_ERROR_MESSAGE);
        }
    }

    private String toPrettyString(List<?> list) {
        return list.stream().map(x-> x.toString() + "\n").collect(Collectors.joining());
    }
}
