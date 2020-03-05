package io.steamreviewbot.domain;

import java.awt.image.BufferedImage;
import java.util.Date;

public class PostInformations {
	
	private String username;
	private BufferedImage userProfilePic;
	private Integer products;
	private Integer reviews;
	private Boolean upvote;
	private Float timeOnRecord;
	private Float timeOnLastWeeks;
	private Date created;
	private String reviewBody;
	private Integer helpful;
	private Integer funny;
	
	public PostInformations() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BufferedImage getUserProfilePic() {
		return userProfilePic;
	}

	public void setUserProfilePic(BufferedImage userProfilePic) {
		this.userProfilePic = userProfilePic;
	}

	public Integer getProducts() {
		return products;
	}

	public void setProducts(Integer products) {
		this.products = products;
	}

	public Integer getReviews() {
		return reviews;
	}

	public void setReviews(Integer reviews) {
		this.reviews = reviews;
	}

	public Boolean getUpvote() {
		return upvote;
	}

	public void setUpvote(Boolean upvote) {
		this.upvote = upvote;
	}

	public Float getTimeOnRecord() {
		return timeOnRecord;
	}

	public void setTimeOnRecord(Float timeOnRecord) {
		this.timeOnRecord = timeOnRecord;
	}

	public Float getTimeOnLastWeeks() {
		return timeOnLastWeeks;
	}

	public void setTimeOnLastWeeks(Float timeOnLastWeeks) {
		this.timeOnLastWeeks = timeOnLastWeeks;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getReviewBody() {
		return reviewBody;
	}

	public void setReviewBody(String reviewBody) {
		this.reviewBody = reviewBody;
	}

	public Integer getHelpful() {
		return helpful;
	}

	public void setHelpful(Integer helpful) {
		this.helpful = helpful;
	}

	public Integer getFunny() {
		return funny;
	}

	public void setFunny(Integer funny) {
		this.funny = funny;
	}

	@Override
	public String toString() {
		return "PostInformations [username=" + username + ", userProfilePic=" + "IMAGEM" + ", products="
				+ products + ", reviews=" + reviews + ", upvote=" + upvote + ", timeOnRecord=" + timeOnRecord
				+ ", timeOnLastWeeks=" + timeOnLastWeeks + ", created=" + created + ", reviewBody=" + reviewBody
				+ ", helpful=" + helpful + ", funny=" + funny + "]";
	}


}
