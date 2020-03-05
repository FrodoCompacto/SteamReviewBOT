package io.steamreviewbot.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuerySummary {
	@SerializedName("num_reviews")
    @Expose
    private Integer numReviews;
    @SerializedName("review_score")
    @Expose
    private Integer reviewScore;
    @SerializedName("review_score_desc")
    @Expose
    private String reviewScoreDesc;
    @SerializedName("total_positive")
    @Expose
    private Integer totalPositive;
    @SerializedName("total_negative")
    @Expose
    private Integer totalNegative;
    @SerializedName("total_reviews")
    @Expose
    private Integer totalReviews;

    public Integer getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
    }

    public Integer getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Integer reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getReviewScoreDesc() {
        return reviewScoreDesc;
    }

    public void setReviewScoreDesc(String reviewScoreDesc) {
        this.reviewScoreDesc = reviewScoreDesc;
    }

    public Integer getTotalPositive() {
        return totalPositive;
    }

    public void setTotalPositive(Integer totalPositive) {
        this.totalPositive = totalPositive;
    }

    public Integer getTotalNegative() {
        return totalNegative;
    }

    public void setTotalNegative(Integer totalNegative) {
        this.totalNegative = totalNegative;
    }

    public Integer getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Integer totalReviews) {
        this.totalReviews = totalReviews;
    }
}
