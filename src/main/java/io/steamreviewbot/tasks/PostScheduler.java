package io.steamreviewbot.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.steamreviewbot.services.PostService;

import java.io.IOException;
import java.util.Random;

import org.json.JSONException;
import twitter4j.TwitterException;

@Component
@EnableScheduling
public class PostScheduler {
	
	@Autowired
	private PostService postService;
	

	@Scheduled(fixedDelay = 5400000)
	 public void scheduledEvent() throws JSONException, IOException, TwitterException {
		if (getRandomNumberInRange(1,500) < 35){
			postService.generateNewDualityPost();
		} else postService.generateNewPost();
    }

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("Max must be greater than min.");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
