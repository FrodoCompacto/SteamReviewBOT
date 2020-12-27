package io.steamreviewbot.dto;

import java.util.ArrayList;
import java.util.List;

import io.steamreviewbot.repositories.PostRepository.IAppStats;



public class AppPostStats {
	
	List<AppStats> appList = new ArrayList<>();
	
	public List<AppStats> getAppList() {
		return appList;
	}
	public void setAppList(List<AppStats> appList) {
		this.appList = appList;
	}
	public Integer getTotalItens() {
		return totalItens;
	}
	public void setTotalItens(Integer totalItens) {
		this.totalItens = totalItens;
	}
	Integer totalItens;
	
	public void setListFromInterface(List<IAppStats> list) {
		
		list.forEach(item -> {
			appList.add(new AppStats(item));
		});
		
	}

}