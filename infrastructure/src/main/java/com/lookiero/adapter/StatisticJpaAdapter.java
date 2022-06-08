package com.lookiero.adapter;

import com.lookiero.entity.Statistic;
import com.lookiero.mapper.StatisticMapper;
import com.lookiero.repository.StatisticRepository;
import com.lookiero.data.StatisticDto;
import com.lookiero.ports.spi.StatisticPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticJpaAdapter implements StatisticPersistencePort {

    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public StatisticDto add(final StatisticDto statisticDto) {
        final Statistic statistic = StatisticMapper.INSTANCE.statisticDtoToStatistic(statisticDto);

        final Statistic statisticSaved = statisticRepository.save(statistic);

        return StatisticMapper.INSTANCE.statisticToStatisticDto(statisticSaved);
    }

    @Override
    public StatisticDto update(final StatisticDto statisticDto) {
        return this.add(statisticDto);
    }

    @Override
    public List<StatisticDto> findAll() {
        final List<Statistic> statisticList = statisticRepository.findAllByOrderByAverageBmi();

        return StatisticMapper.INSTANCE.statisticListToStatisticDtoList(statisticList);
    }

    @Override
    public Optional<StatisticDto> findByBirthYear(final Integer birthYear) {
        final Optional<Statistic> statistic = this.statisticRepository.findByBirthYear(birthYear);

        return statistic.map(StatisticMapper.INSTANCE::statisticToStatisticDto);
    }
}
