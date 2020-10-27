package io.steamreviewbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.steamreviewbot.repositories.PageStatisticsRepository;

@Service
public class PageStatisticsService {

	@SuppressWarnings("unused")
	@Autowired
	private PageStatisticsRepository repo;
	
	
}
