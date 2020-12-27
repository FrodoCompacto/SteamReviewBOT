package io.steamreviewbot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.steamreviewbot.domain.Post;



@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	
	@Query("SELECT obj FROM Post obj WHERE obj.appId = ?1")
	List<Post> getReviews(int appId);
	
	@Query("SELECT obj.appId AS appid, count(*) AS count FROM Post obj WHERE obj.appId = ?1")
	IAppStats getPostedTimes(int appId);
	
	@Query("SELECT obj.appId AS appid, count(*) AS count FROM Post obj GROUP BY obj.appId ORDER BY count(*) DESC")
	List<IAppStats> getTopPosted();
	
	
	public interface IAppStats {
		
		Integer getAppid();
		Integer getCount();
	}
	
}

