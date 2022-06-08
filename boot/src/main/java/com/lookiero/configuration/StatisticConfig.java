package com.lookiero.configuration;

import com.lookiero.adapter.StatisticJpaAdapter;
import com.lookiero.controller.statistic.StatisticCliController;
import com.lookiero.ports.api.StatisticServicePort;
import com.lookiero.ports.spi.StatisticPersistencePort;
import com.lookiero.service.StatisticServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatisticConfig {

    @Bean
    public StatisticCliController statisticCliController(){
        return new StatisticCliController();
    }

    @Bean
    public StatisticPersistencePort statisticPersistence(){
        return new StatisticJpaAdapter();
    }

    @Bean
    public StatisticServicePort statisticService(){
        return new StatisticServiceImpl(statisticPersistence());
    }
}
