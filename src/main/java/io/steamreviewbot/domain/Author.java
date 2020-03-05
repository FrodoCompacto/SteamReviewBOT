package io.steamreviewbot.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {
	@SerializedName("steamid")
	@Expose
	private String steamid;
	@SerializedName("num_games_owned")
	@Expose
	private Integer numGamesOwned;
	@SerializedName("num_reviews")
	@Expose
	private Integer numReviews;
	@SerializedName("playtime_forever")
	@Expose
	private Integer playtimeForever;
	@SerializedName("playtime_last_two_weeks")
	@Expose
	private Integer playtimeLastTwoWeeks;
	@SerializedName("last_played")
	@Expose
	private Integer lastPlayed;

	public String getSteamid() {
		return steamid;
	}

	public void setSteamid(String steamid) {
		this.steamid = steamid;
	}

	public Integer getNumGamesOwned() {
		return numGamesOwned;
	}

	public void setNumGamesOwned(Integer numGamesOwned) {
		this.numGamesOwned = numGamesOwned;
	}

	public Integer getNumReviews() {
		return numReviews;
	}

	public void setNumReviews(Integer numReviews) {
		this.numReviews = numReviews;
	}

	public Integer getPlaytimeForever() {
		return playtimeForever;
	}

	public void setPlaytimeForever(Integer playtimeForever) {
		this.playtimeForever = playtimeForever;
	}

	public Integer getPlaytimeLastTwoWeeks() {
		return playtimeLastTwoWeeks;
	}

	public void setPlaytimeLastTwoWeeks(Integer playtimeLastTwoWeeks) {
		this.playtimeLastTwoWeeks = playtimeLastTwoWeeks;
	}

	public Integer getLastPlayed() {
		return lastPlayed;
	}

	public void setLastPlayed(Integer lastPlayed) {
		this.lastPlayed = lastPlayed;
	}
}
