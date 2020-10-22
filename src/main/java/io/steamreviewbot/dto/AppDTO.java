package io.steamreviewbot.dto;

import java.io.Serializable;

import io.steamreviewbot.domain.App;

public class AppDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer appid;
	private String name;
	
	public AppDTO() {
		super();
	}

	public AppDTO(Integer appid, String name) {
		super();
		this.appid = appid;
		this.name = name;
	}

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public App toApp(int iScore) {
		return new App(this.appid, this.name, iScore);
	}
	
	


}
