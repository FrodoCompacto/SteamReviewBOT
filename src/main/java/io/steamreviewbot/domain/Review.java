package io.steamreviewbot.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {
	@SerializedName("recommendationid")
	@Expose
	private String recommendationid;
	@SerializedName("author")
	@Expose
	private Author author;
	@SerializedName("language")
	@Expose
	private String language;
	@SerializedName("review")
	@Expose
	private String review;
	@SerializedName("timestamp_created")
	@Expose
	private Integer timestampCreated;
	@SerializedName("timestamp_updated")
	@Expose
	private Integer timestampUpdated;
	@SerializedName("voted_up")
	@Expose
	private Boolean votedUp;
	@SerializedName("votes_up")
	@Expose
	private Integer votesUp;
	@SerializedName("votes_funny")
	@Expose
	private Integer votesFunny;
	@SerializedName("weighted_vote_score")
	@Expose
	private String weightedVoteScore;
	@SerializedName("comment_count")
	@Expose
	private Integer commentCount;
	@SerializedName("steam_purchase")
	@Expose
	private Boolean steamPurchase;
	@SerializedName("received_for_free")
	@Expose
	private Boolean receivedForFree;
	@SerializedName("written_during_early_access")
	@Expose
	private Boolean writtenDuringEarlyAccess;

	public String getRecommendationid() {
		return recommendationid;
	}

	public void setRecommendationid(String recommendationid) {
		this.recommendationid = recommendationid;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Integer getTimestampCreated() {
		return timestampCreated;
	}

	public void setTimestampCreated(Integer timestampCreated) {
		this.timestampCreated = timestampCreated;
	}

	public Integer getTimestampUpdated() {
		return timestampUpdated;
	}

	public void setTimestampUpdated(Integer timestampUpdated) {
		this.timestampUpdated = timestampUpdated;
	}

	public Boolean getVotedUp() {
		return votedUp;
	}

	public void setVotedUp(Boolean votedUp) {
		this.votedUp = votedUp;
	}

	public Integer getVotesUp() {
		return votesUp;
	}

	public void setVotesUp(Integer votesUp) {
		this.votesUp = votesUp;
	}

	public Integer getVotesFunny() {
		return votesFunny;
	}

	public void setVotesFunny(Integer votesFunny) {
		this.votesFunny = votesFunny;
	}

	public String getWeightedVoteScore() {
		return weightedVoteScore;
	}

	public void setWeightedVoteScore(String weightedVoteScore) {
		this.weightedVoteScore = weightedVoteScore;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Boolean getSteamPurchase() {
		return steamPurchase;
	}

	public void setSteamPurchase(Boolean steamPurchase) {
		this.steamPurchase = steamPurchase;
	}

	public Boolean getReceivedForFree() {
		return receivedForFree;
	}

	public void setReceivedForFree(Boolean receivedForFree) {
		this.receivedForFree = receivedForFree;
	}

	public Boolean getWrittenDuringEarlyAccess() {
		return writtenDuringEarlyAccess;
	}

	public void setWrittenDuringEarlyAccess(Boolean writtenDuringEarlyAccess) {
		this.writtenDuringEarlyAccess = writtenDuringEarlyAccess;
	}
}
