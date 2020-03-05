package io.steamreviewbot.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.steamreviewbot.services.PostService;

import java.io.IOException;

import org.json.JSONException;

@Component
@EnableScheduling
public class PostScheduler {
	
	@Autowired
	private PostService postService;
	

	@Scheduled(fixedDelay = 600000)
	 public void scheduledEvent() throws JSONException, IOException { 
		postService.generateNewPost();
    }
	
}
