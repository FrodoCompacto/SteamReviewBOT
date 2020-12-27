package io.steamreviewbot.dto;

import io.steamreviewbot.repositories.PostRepository.IAppStats;

public class AppStats {
	
	Integer appId;
	Integer count;
	
	
	public AppStats() {
		super();
	}
	public AppStats(Integer appId, Integer count) {
		super();
		this.appId = appId;
		this.count = count;
	}
	
	public AppStats (IAppStats item) {
		super();
		this.appId = item.getAppid();
		this.count = item.getCount();
		
	}
	
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	

	
}
