package com.lookiero.mapper;

import com.lookiero.data.StatisticDto;
import com.lookiero.entity.Statistic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StatisticMapper {

    StatisticMapper INSTANCE = Mappers.getMapper(StatisticMapper.class);

    Statistic statisticDtoToStatistic(final StatisticDto statisticDto);

    StatisticDto statisticToStatisticDto(final Statistic statistic);

    List<StatisticDto> statisticListToStatisticDtoList(final List<Statistic> statisticList);
}
