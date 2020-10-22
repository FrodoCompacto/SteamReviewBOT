package io.steamreviewbot.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class App implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer app_id;
	
	private String  name;
	private Integer interestRate;
	

	public App() {
		super();
	}


	public App(Integer app_id, String name, Integer interestRate) {
		super();
		this.app_id = app_id;
		this.name = name;
		this.interestRate = interestRate;
	}


	public Integer getApp_id() {
		return app_id;
	}


	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getInterestRate() {
		return interestRate;
	}


	public void setInterestRate(Integer interestRate) {
		this.interestRate = interestRate;
	}

	public void addInterest() {
		this.interestRate++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((app_id == null) ? 0 : app_id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		App other = (App) obj;
		if (app_id == null) {
			if (other.app_id != null)
				return false;
		} else if (!app_id.equals(other.app_id))
			return false;
		return true;
	}

}
