package io.steamreviewbot.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
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
import io.steamreviewbot.dto.AppPostStats;
import io.steamreviewbot.dto.AppStats;
import io.steamreviewbot.repositories.AppRepository;
import io.steamreviewbot.repositories.PostRepository;
import twitter4j.TwitterException;

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
    private PostRepository postRepo;
    
    @Autowired
    private AppRepository appRepo;

    @Autowired
    private TwitterService ttService;

    //private List<String> gamesList = Arrays.asList("578080", "238320", "295110", "594570", "253710", "629760", "113420", "698780", "548430", "265930", "261570", "364470", "113400", "206500", "35700", "883710", "262410", "319630", "333930", "55100", "460930", "4760", "367520", "370910", "379720", "1250", "893520", "207610", "236390", "271590", "304390", "55110", "466240", "204100", "225260", "730310", "365590", "32400", "601150", "251570", "343710", "239200", "282070", "287700", "221910", "273350", "273110", "530700", "204360", "17460", "319830", "871720", "444200", "230410", "113200", "677620", "475150", "261110", "4560", "17470", "389570", "55150", "620", "327690", "630100", "205230", "67370", "203290", "884660", "4570", "8930", "219640", "630", "337000", "15700", "550900", "113020", "513710", "244850", "812140", "298240", "400", "845070", "489520", "225080", "259080", "674940", "21120", "272060", "239030", "444640", "255710", "105600", "233720", "377160", "266840", "209870", "700330", "555570", "420", "620980", "394510", "21100", "275390", "17080", "215080", "286690", "63380", "265590", "368230", "108710", "8980", "207230", "21110", "8500", "32470", "339610", "26800", "440", "305620", "391460", "391220", "252490", "222880", "279720", "381210", "335240", "766570", "262060", "7670", "208580", "427270", "209670", "322170", "223710", "220440", "220200", "220", "35720", "211500", "355180", "1174180", "223750", "612880", "220240", "268420", "438100", "601510", "65930", "447040", "41700", "240", "431960", "221100", "208140", "323580", "234650", "224600", "578310", "356190", "21090", "349700", "424370", "209000", "12110", "299360", "312660", "335300", "15620", "809960", "107100", "10180", "236850", "12120", "834910", "280", "611500", "287390", "363970", "237930", "508440", "302830", "65980", "233130", "248820", "35450", "291650", "96000", "40800", "417860", "200210", "22370", "280790", "242050", "250900", "212680", "9450", "221380", "22380", "201790", "12100", "301520", "3900", "72850", "372000", "33930", "493340", "3910", "227940", "39210", "286940", "9480", "270550", "552990", "552500", "214420", "205950", "203770", "200260", "225540", "50620", "813820", "237310", "247120", "395170", "436520", "32800", "17410", "648800", "285800", "70000", "41500", "291480", "232090", "252950", "204880", "104900", "582160", "261640", "8190", "224260", "2400", "307690", "389730", "342200", "3990", "99900", "706990", "306130", "715210", "365670", "20570", "391720", "310950", "346900", "317360", "386360", "558100", "227300", "275850", "304050", "242920", "364360", "264710", "365450", "212070", "214490", "238460", "380600", "218620", "316010", "17390", "55230", "307780", "240320", "294100", "813780", "203140", "303390", "228200", "976730", "581320", "308600", "244930", "20540", "453480", "206420", "389430", "550650", "582660", "10500", "242720", "219740", "254700", "730", "205100", "203160", "39120", "238010", "6860", "206440", "219990", "386180", "594650", "3590", "57690", "12900", "500", "466560", "431240", "47410", "206210", "779340", "323190", "47890", "8800", "433850", "387290", "6880", "218680", "379430", "24780", "326460", "9900", "491950", "268910", "409710", "242760", "391540", "208650", "22330", "33230", "407530", "115300", "1172380", "4000", "265630", "32370", "239140", "300", "44350", "286160", "211820", "8850", "7760", "48700", "1089350", "218230", "252150", "397900", "905370", "550", "286570", "41070", "232770", "24740", "239160", "200710", "274170", "489830", "320", "24980", "231430", "8870", "108800", "210770", "323370", "346010", "755790", "201810", "252130", "570", "511470", "24960", "238090", "340", "21690", "107410", "10090", "207140", "346110", "291550", "359550", "510840", "10", "109600", "6000", "40970", "838350", "20900", "35140", "360", "42910", "200510", "359320", "761890", "20", "105450", "233450", "38400", "130", "110800", "30", "212500", "6020", "268500", "274190", "814380", "380", "34030", "219150", "24240", "49520", "211420", "40", "214950", "311690", "427520", "646570", "311210", "6030", "202970", "361420", "435150", "224760", "50", "588650", "394360", "444090", "462780", "270880", "60", "304930", "325610", "278360", "202750", "50130", "70", "12210", "16810", "863550", "588430", "47790", "322330", "12220", "24200", "6060", "413150", "1840", "80", "47780", "281990", "638070", "238960", "9340", "310560", "457140", "1046930", "213670", "312990", "221040", "530620", "439700", "563560", "16450", "234140", "374320", "269210", "208090", "239820", "744900", "292030", "331470", "241930", "442080", "248570", "250400", "3830", "582010", "504370", "644560", "506540", "102600", "1085660", "477160", "355840", "42700", "518790", "48000", "4700", "844870", "236110", "212160", "440900", "20920", "204300", "632360", "289070", "417910", "50300", "282440", "782330", "952060", "397540", "546560");

    public List<Post> getPostsAfterDate(Date date){
        return postRepo.getPostBetween(date, new Date());
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
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

    public void generateNewPost() throws JSONException, IOException, TwitterException {

        List<Post> postList;
        Review review;
        int reviewIndex;
        ReviewsPage page;
        int appId;
        String postId;

        do {
            do {
            	appId = appRepo.getRandomApp().getApp_id();
            			
                JSONObject json = jsonService.readJsonFromUrl(
                        "https://store.steampowered.com/appreviews/" + appId + "?json=1");
                Gson gson = new Gson();
                page = gson.fromJson(json.toString(), ReviewsPage.class);
            } while (page.getReviews().size() < 1);
            if (page.getReviews().size() == 1) {
                reviewIndex = 0;
            } else {
                reviewIndex = getRandomNumberInRange(0, page.getReviews().size() - 1);
            }

            review = page.getReviews().get(reviewIndex);

        } while (review.getReview().length() > 250 || review.getReview().length() < 1
                || hasDuplicates(postRepo.getReviews(appId), review.getReview()));

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
        Instant instant = Instant.ofEpochSecond( review.getTimestampCreated() );
        newPost.setCreated(Date.from( instant ));
        newPost.setReviewBody(review.getReview());
        newPost.setHelpful(review.getVotesUp());
        newPost.setFunny(review.getVotesFunny());

        String postComment = "Game reviewed:\n" + "https://store.steampowered.com/app/" + appId
                + "/";

        InputStream img = imgService.getInputStream(imgService.genNewReviewImage(newPost), "jpg");
        Path tempFile = Files.createTempFile("aux", ".jpg"); // prefixo e sufixo podem ser null (tanto faz, o arquivo é temporário e o Java cria um nome "único" mesmo...)
        Files.copy(img, tempFile, StandardCopyOption.REPLACE_EXISTING);

        postId = fbService.newFacebookPost(Files.newInputStream(tempFile, StandardOpenOption.READ), postComment);
        long tweetId = ttService.newTweet(Files.newInputStream(tempFile, StandardOpenOption.READ), "", postComment);
        postRepo.save(new Post(null, review.getReview(), appId, postId, tweetId));

        Files.delete(tempFile);

        //imgService.copyInputStreamToFile(imgService.getInputStream(imgService.genNewReviewImage(newPost), "jpg"), "c:\\test\\test.jpg");

		//System.out.println("GAME ID: " + appId);
		//System.out.println("REVIEW INDEX: " + reviewIndex);
		//System.out.println("REVIEW: " + review.getReview());

    }

    public void generateNewDualityPost() throws JSONException, IOException, TwitterException {

        List<Post> postList;
        Review review_positive;
        Review review_negative;

        ReviewsPage page;
        int appId;

        String postId;

            do {
                review_positive = null;
                review_negative = null;

                do {
                    appId = appRepo.getRandomApp().getApp_id();

                    JSONObject json = jsonService.readJsonFromUrl(
                            "https://store.steampowered.com/appreviews/" + appId + "?json=1&review_type=negative");
                    Gson gson = new Gson();
                    page = gson.fromJson(json.toString(), ReviewsPage.class);

                } while (page.getReviews().size() < 1);

                postList = postRepo.getReviews(appId);

                for (Review r : page.getReviews()) {
                    if (review_negative == null){
                        if (r.getReview().length() < 150 && r.getReview().length() > 1
                                && !hasDuplicates(postList, r.getReview())){
                            review_negative = r;
                        }
                    }
                }

                JSONObject json = jsonService.readJsonFromUrl(
                        "https://store.steampowered.com/appreviews/" + appId + "?json=1&review_type=positive");
                Gson gson = new Gson();
                page = gson.fromJson(json.toString(), ReviewsPage.class);

                if (page.getReviews().size() > 0){
                    for (Review r : page.getReviews()) {
                        if (review_positive == null){
                            if (r.getReview().length() < 150 && r.getReview().length() > 1
                                    && !hasDuplicates(postList, r.getReview())){
                                review_positive = r;
                            }
                        }
                    }
                }

            } while (review_negative == null || review_positive == null);


        User profileInfo = fetchUser(review_positive.getAuthor().getSteamid());
        URL url = new URL(profileInfo.getResponse().getPlayers().get(0).getAvatar());

        PostInformations Post1 = new PostInformations();
        Post1.setUsername(profileInfo.getResponse().getPlayers().get(0).getPersonaname());
        Post1.setUserProfilePic(ImageIO.read(url));
        Post1.setProducts(review_positive.getAuthor().getNumGamesOwned());
        Post1.setReviews(review_positive.getAuthor().getNumReviews());
        Post1.setUpvote(true);
        Post1.setTimeOnRecord(fixTime(review_positive.getAuthor().getPlaytimeForever()));
        Post1.setTimeOnLastWeeks(fixTime(review_positive.getAuthor().getPlaytimeLastTwoWeeks()));
        Instant instant = Instant.ofEpochSecond( review_positive.getTimestampCreated() );
        Post1.setCreated(Date.from( instant ));
        Post1.setReviewBody(review_positive.getReview());
        Post1.setHelpful(0);
        Post1.setFunny(0);

        profileInfo = fetchUser(review_negative.getAuthor().getSteamid());
        url = new URL(profileInfo.getResponse().getPlayers().get(0).getAvatar());

        PostInformations Post2 = new PostInformations();
        Post2.setUsername(profileInfo.getResponse().getPlayers().get(0).getPersonaname());
        Post2.setUserProfilePic(ImageIO.read(url));
        Post2.setProducts(review_negative.getAuthor().getNumGamesOwned());
        Post2.setReviews(review_negative.getAuthor().getNumReviews());
        Post2.setUpvote(false);
        Post2.setTimeOnRecord(fixTime(review_negative.getAuthor().getPlaytimeForever()));
        Post2.setTimeOnLastWeeks(fixTime(review_negative.getAuthor().getPlaytimeLastTwoWeeks()));
        instant = Instant.ofEpochSecond( review_negative.getTimestampCreated() );
        Post2.setCreated(Date.from( instant ));
        Post2.setReviewBody(review_negative.getReview());
        Post2.setHelpful(0);
        Post2.setFunny(0);

        String postComment = "Game reviewed:\n" + "https://store.steampowered.com/app/" + appId
                + "/";
        String desc = "The duality of man....";

        InputStream img = imgService.getInputStream(imgService.genNewDualityImage(Post1, Post2), "jpg");
        Path tempFile = Files.createTempFile("aux", ".jpg"); // prefixo e sufixo podem ser null (tanto faz, o arquivo é temporário e o Java cria um nome "único" mesmo...)
        Files.copy(img, tempFile, StandardCopyOption.REPLACE_EXISTING);

        postId = fbService.newFacebookPostWDesc(Files.newInputStream(tempFile, StandardOpenOption.READ), postComment, desc);
        long tweetId = ttService.newTweet(Files.newInputStream(tempFile, StandardOpenOption.READ), desc, postComment);
        postRepo.save(new Post(null, review_positive.getReview(), appId, "DUALITY_1_" + postId, tweetId));
        postRepo.save(new Post(null, review_negative.getReview(), appId, "DUALITY_2_" + postId, tweetId));

        Files.delete(tempFile);

        //imgService.copyInputStreamToFile(imgService.getInputStream(imgService.genNewDualityImage(Post1, Post2), "jpg"), "c:\\test\\test.jpg");
    }

    private User fetchUser(String userid) throws JSONException, IOException {
        JSONObject json = jsonService
                .readJsonFromUrl("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=" + steamKEY
                        + "&steamids=" + userid);
        Gson gson = new Gson();
        return gson.fromJson(json.toString(), User.class);
    }

	public AppPostStats findTopPosts(int limit) {
		
		AppPostStats stats = new AppPostStats();
	
		stats.setListFromInterface(postRepo.getTopPosted().subList(0, limit));
		stats.setTotalItens((int)postRepo.count());
		
		return stats;
	}
	
	public AppStats findAppInfo(int id) {
		
		return new AppStats(postRepo.getPostedTimes(id));
	}
}
