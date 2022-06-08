package com.lookiero.configuration;

import com.lookiero.commandlineinterface.CommandLineInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandLineInterfaceConfig {
    @Bean
    public CommandLineInterface commandLineInterface(final UserConfig userConfig,
                                                     final StatisticConfig statisticConfig) {
        return new CommandLineInterface(userConfig.userCliController(), statisticConfig.statisticCliController());
    }

}