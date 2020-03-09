package io.steamreviewbot.services;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import io.steamreviewbot.domain.Post;
import io.steamreviewbot.domain.PostInformations;
import io.steamreviewbot.domain.Review;
import io.steamreviewbot.domain.ReviewsPage;
import io.steamreviewbot.domain.user.User;
import io.steamreviewbot.repositories.PostRepository;

@Service
public class PostService {

	@Value("${steamapi.key}")
	private String steamKEY;

	@Autowired
	private JsonService jsonService;

	@Autowired
	private ImageService imgService;

	@Autowired
	private FacebookService fbService;

	@Autowired
	private PostRepository repo;

	private List<String> gamesList = Arrays.asList("578080", "346110", "291550", "253710", "359550", "346900", "10",
			"109600", "113400", "386360", "360", "240", "227300", "304050", "431960", "319630", "333930", "130", "30",
			"218620", "370910", "380", "236390", "271590", "49520", "50", "444090", "10180", "273350", "550650",
			"273110", "204360", "363970", "60", "304930", "582660", "219740", "278360", "96000", "444200", "230410",
			"730", "203160", "70", "863550", "588430", "322330", "219990", "413150", "221380", "620", "500", "80",
			"301520", "8930", "72850", "238960", "219640", "15700", "433850", "1046930", "227940", "400", "439700",
			"272060", "552990", "407530", "552500", "255710", "105600", "208090", "377160", "4000", "700330", "555570",
			"292030", "239140", "420", "300", "44350", "275390", "218230", "550", "582010", "285800", "291480", "440",
			"252950", "320", "231430", "1085660", "252490", "755790", "477160", "570", "224260", "381210", "340", "220",
			"417910", "730", "570", "359550", "271590", "578080", "346110", "440", "582010", "1085660", "252490",
			"252950", "1100600", "230410", "394360", "292030", "620", "105600", "400", "413150", "10", "550", "220",
			"698780", "4000", "205100", "447530", "736260", "504230", "70", "420110", "238320", "374320", "999020",
			"991560", "250900", "219150", "242760", "13230", "286160", "317400", "102600");

	private List<Post> postList;

	public void generateNewPost() throws JSONException, IOException {

		Review review;
		int gameIndex;
		int reviewIndex;
		ReviewsPage page;

		do {
			do {
				gameIndex = getRandomNumberInRange(0, gamesList.size() - 1);
				
				JSONObject json = jsonService.readJsonFromUrl(
						"https://store.steampowered.com/appreviews/" + gamesList.get(gameIndex) + "?json=1");
				Gson gson = new Gson();
				page = gson.fromJson(json.toString(), ReviewsPage.class);
			} while (page.getReviews().size() < 1);
			if (page.getReviews().size() == 1) {
				reviewIndex = 0;
			} else {
				reviewIndex = getRandomNumberInRange(0, page.getReviews().size() - 1);
			}
			
			review = page.getReviews().get(reviewIndex);
			postList = repo.getReviews(Integer.parseInt(gamesList.get(gameIndex)));
		} while (review.getReview().length() > 250 || review.getReview().length() < 1
				|| hasDuplicates(postList, review.getReview()));

		User profileInfo = fetchUser(review.getAuthor().getSteamid());
		URL url = new URL(profileInfo.getResponse().getPlayers().get(0).getAvatar());

		PostInformations newPost = new PostInformations();
		newPost.setUsername(profileInfo.getResponse().getPlayers().get(0).getPersonaname());
		newPost.setUserProfilePic(ImageIO.read(url));
		newPost.setProducts(review.getAuthor().getNumGamesOwned());
		newPost.setReviews(review.getAuthor().getNumReviews());
		newPost.setUpvote(review.getVotedUp());
		newPost.setTimeOnRecord(fixTime(review.getAuthor().getPlaytimeForever()));
		newPost.setTimeOnLastWeeks(fixTime(review.getAuthor().getPlaytimeLastTwoWeeks()));
		newPost.setCreated(new Date(review.getTimestampCreated() * 1000));
		newPost.setReviewBody(review.getReview());
		newPost.setHelpful(review.getVotesUp());
		newPost.setFunny(review.getVotesFunny());

		String postComment = "Game reviewed:\n" + "https://store.steampowered.com/app/" + gamesList.get(gameIndex)
				+ "/";

		fbService.newFacebookPost(imgService.getInputStream(imgService.genNewReviewImage(newPost), "jpg"), postComment);
		repo.save(new Post(null, review.getReview(), Integer.parseInt(gamesList.get(gameIndex))));
		
//		System.out.println("GAME INDEX: " + gameIndex);
//		System.out.println("REVIEW INDEX: " + reviewIndex);
//		System.out.println("REVIEW: " + review.getReview());
			
	}

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("Max must be greater than min.");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	private User fetchUser(String userid) throws JSONException, IOException {
		JSONObject json = jsonService
				.readJsonFromUrl("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=" + steamKEY
						+ "&steamids=" + userid);
		Gson gson = new Gson();
		return gson.fromJson(json.toString(), User.class);
	}

	private static Float fixTime(Integer time) {
		return (float) time / 60;
	}

	private static boolean hasDuplicates(List<Post> list, String review) {
		for (Post post : list) {
			if (post.getReviewBody().equals(review))
				return true;
		}
		return false;
	}
}
