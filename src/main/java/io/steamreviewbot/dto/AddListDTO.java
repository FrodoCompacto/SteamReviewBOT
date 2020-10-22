package io.steamreviewbot.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class AddListDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private ArrayList<AppDTO> appids;
	private String captchaResponse;
	
	
	public ArrayList<AppDTO> getAppids() {
		return appids;
	}
	public void setAppids(ArrayList<AppDTO> appids) {
		this.appids = appids;
	}
	public String getCaptchaResponse() {
		return captchaResponse;
	}
	public void setCaptchaResponse(String captchaResponse) {
		this.captchaResponse = captchaResponse;
	}
	
	
	

}
