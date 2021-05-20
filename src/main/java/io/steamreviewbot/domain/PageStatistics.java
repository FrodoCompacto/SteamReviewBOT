package io.steamreviewbot.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PageStatistics implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date postDate;
	
	private Integer likes;
	private Integer loves;
	private Integer cares;
	private Integer hahas;
	private Integer wows;
	private Integer sads;
	private Integer grrs;
	
	@ElementCollection
    @CollectionTable(name="listOfPosts")
	private List<Integer> post_ids;

	public PageStatistics() {
		super();
		this.post_ids = new ArrayList<>();
		this.postDate = new Date();

		this.likes = 0;
		this.loves = 0;
		this.cares = 0;
		this.hahas = 0;
		this.wows = 0;
		this.sads = 0;
		this.grrs = 0;
	}

	public PageStatistics(Integer id, Integer likes, Integer loves, Integer cares, Integer hahas,
			Integer wows, Integer sads, Integer grrs, List<Integer> post_ids) {
		super();
		this.id = id;
		this.postDate = new Date();
		this.likes = likes;
		this.loves = loves;
		this.cares = cares;
		this.hahas = hahas;
		this.wows = wows;
		this.sads = sads;
		this.grrs = grrs;
		this.post_ids = post_ids;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getLoves() {
		return loves;
	}

	public void setLoves(Integer loves) {
		this.loves = loves;
	}

	public Integer getCares() {
		return cares;
	}

	public void setCares(Integer cares) {
		this.cares = cares;
	}

	public Integer getHahas() {
		return hahas;
	}

	public void setHahas(Integer hahas) {
		this.hahas = hahas;
	}

	public Integer getWows() {
		return wows;
	}

	public void setWows(Integer wows) {
		this.wows = wows;
	}

	public Integer getSads() {
		return sads;
	}

	public void setSads(Integer sads) {
		this.sads = sads;
	}

	public Integer getGrrs() {
		return grrs;
	}

	public void setGrrs(Integer grrs) {
		this.grrs = grrs;
	}

	public List<Integer> getPost_ids() {
		return post_ids;
	}

	public void setPost_ids(List<Integer> post_ids) {
		this.post_ids = post_ids;
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
		PageStatistics other = (PageStatistics) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
