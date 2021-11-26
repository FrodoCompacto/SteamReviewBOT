package io.steamreviewbot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.steamreviewbot.domain.App;


@Repository
public interface AppRepository extends JpaRepository<App, Integer> {
	
	@Query(value="SELECT * FROM app ORDER BY RAND() LIMIT 1", nativeQuery = true)
	App getRandomApp();
	
}
