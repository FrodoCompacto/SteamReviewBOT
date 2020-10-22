package io.steamreviewbot.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String reviewBody;
	private Integer appId;
	private String postId;
	
	
	public Post() {
		super();
	}


	public Post(Integer id, String reviewBody, Integer appId, String postId) {
		super();
		this.id = id;
		this.reviewBody = reviewBody;
		this.appId = appId;
		this.postId = postId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getReviewBody() {
		return reviewBody;
	}


	public void setReviewBody(String reviewBody) {
		this.reviewBody = reviewBody;
	}


	public Integer getAppId() {
		return appId;
	}


	public void setAppId(Integer appId) {
		this.appId = appId;
	}


	public String getPostId() {
		return postId;
	}


	public void setPostId(String postId) {
		this.postId = postId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


		
	
}
