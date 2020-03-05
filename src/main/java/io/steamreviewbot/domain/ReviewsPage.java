package io.steamreviewbot.domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewsPage {
	@SerializedName("success")
	@Expose
	private Integer success;
	@SerializedName("query_summary")
	@Expose
	private QuerySummary querySummary;
	@SerializedName("reviews")
	@Expose
	private List<Review> reviews = null;
	@SerializedName("cursor")
	@Expose
	private String cursor;

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public QuerySummary getQuerySummary() {
		return querySummary;
	}

	public void setQuerySummary(QuerySummary querySummary) {
		this.querySummary = querySummary;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
}
