package com.lookiero.configuration;

import com.lookiero.adapter.UserJpaAdapter;
import com.lookiero.controller.user.UserCliController;
import com.lookiero.ports.api.UserServicePort;
import com.lookiero.ports.spi.UserPersistencePort;
import com.lookiero.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserCliController userCliController(){
        return new UserCliController();
    }

    @Bean
    public UserPersistencePort userPersistence(){
        return new UserJpaAdapter();
    }

    @Bean
    public UserServicePort userService(final StatisticConfig statisticConfig){
        return new UserServiceImpl(userPersistence(), statisticConfig.statisticPersistence());
    }
}
