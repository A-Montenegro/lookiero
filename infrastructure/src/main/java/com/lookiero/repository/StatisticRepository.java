package com.lookiero.repository;

import com.lookiero.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    Optional<Statistic> findByBirthYear(final Integer year);

    List<Statistic> findAllByOrderByAverageBmi();
}
