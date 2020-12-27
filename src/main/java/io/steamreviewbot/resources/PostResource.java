package io.steamreviewbot.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.steamreviewbot.dto.AppPostStats;
import io.steamreviewbot.dto.AppStats;
import io.steamreviewbot.services.PostService;


@RestController
@RequestMapping(value="/post/")
public class PostResource {
	
	@Autowired
	private PostService service;
	
	
	@RequestMapping(value="/top/{limit}", method = RequestMethod.GET)
	public ResponseEntity<AppPostStats> findTop(@PathVariable Integer limit) {
		
		AppPostStats postList = service.findTopPosts(limit);
				
		return ResponseEntity.ok().body(postList);
	}
	
	@RequestMapping(value="/appinfo/{id}", method = RequestMethod.GET)
	public ResponseEntity<AppStats> findAppInfo(@PathVariable Integer id) {
		
		AppStats postList = service.findAppInfo(id);
				
		return ResponseEntity.ok().body(postList);
	}
	
}
