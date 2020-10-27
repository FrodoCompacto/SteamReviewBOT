package io.steamreviewbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.steamreviewbot.domain.PageStatistics;

@Repository
public interface PageStatisticsRepository extends JpaRepository<PageStatistics, Integer> {

}
