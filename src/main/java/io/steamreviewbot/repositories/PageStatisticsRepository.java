package io.steamreviewbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.steamreviewbot.domain.PageStatistics;

import java.util.List;

@Repository
public interface PageStatisticsRepository extends JpaRepository<PageStatistics, Integer> {

    @Query(value="SELECT * FROM page_statistics ORDER BY id DESC LIMIT 1", nativeQuery = true)
    PageStatistics getLastStatsPage();
}
