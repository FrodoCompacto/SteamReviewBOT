package io.steamreviewbot.services;

import java.io.IOException;
import java.io.InputStream;

import com.restfb.*;
import com.restfb.types.*;
import io.steamreviewbot.domain.PostWithDetailedReactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {
	
	@Value("${facebook.pageacess.token}")
    private String pageAccessToken;
	
	String pageID = "102746451339651";

	@Autowired
	private ImageService imgService;


	public String newFacebookPostWDesc(InputStream image, String msg, String desc) {

		FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);
		GraphResponse publishResponse = facebookClient.publish(pageID + "/photos", GraphResponse.class, BinaryAttachment.with("img", image),
				Parameter.with("message", desc));

		commentPost(publishResponse.getId(), msg);
		return publishResponse.getId();
	}

	@SuppressWarnings("deprecation")
	public String newFacebookPost(InputStream image, String msg) {
		
		FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);
		GraphResponse publishResponse = facebookClient.publish(pageID + "/photos", GraphResponse.class, BinaryAttachment.with("img", image));
		
		commentPost(publishResponse.getId(), msg);
		return publishResponse.getId();
	}

	public void commentPost(String postID, String message) {
		
		FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);
		facebookClient.publish(postID + "/comments", 
			       FacebookType.class, Parameter.with("message", message));
	}

	public PostWithDetailedReactions getReactionsFromPost(String postId){
		FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);

		String fields = "id, shares"; // the 'normal' fields
		fields += ",reactions.limit(0).summary(1)" // reactions overview
				+ ",reactions.type(LIKE).limit(0).summary(1).as(reactions_like)" // like reactions
				+ ",reactions.type(LOVE).limit(0).summary(1).as(reactions_love)" // love reactions
				+ ",reactions.type(WOW).limit(0).summary(1).as(reactions_wow)" // wow reactions
				+ ",reactions.type(HAHA).limit(0).summary(1).as(reactions_haha)" // haha reactions
				+ ",reactions.type(SAD).limit(0).summary(1).as(reactions_sad)" // sad reactions
				+ ",reactions.type(ANGRY).limit(0).summary(1).as(reactions_angry)" // angry reactions
				+ ",reactions.type(CARE).limit(0).summary(1).as(reactions_care)"; // thankful reactions

		return facebookClient.fetchObject("102746451339651_" + postId, PostWithDetailedReactions.class, Parameter.with("fields", fields));

	}

	public String getImageUrlFromPost(String postId){

		FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);
		Post post = facebookClient.fetchObject("102746451339651_" + postId, Post.class, Parameter.with("fields","id, full_picture"));


		return post.getFullPicture();
	}

	public String createAlbum(String name){
		FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);

		FacebookType publishPhotoResponse = (FacebookType) facebookClient.publish(pageID + "/albums", FacebookType.class,Parameter.with("name", name));
		return publishPhotoResponse.getId();
	}

	public void publishOnAlbum(String albumId, String imageUrl, String name, String desc) throws IOException {
		FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);

		InputStream is = imgService.inputStreamFromUrl(imageUrl);
		FacebookType photo = facebookClient.publish(pageID + "/" + albumId + "/photos" ,
				FacebookType.class,BinaryAttachment.with(name, is), Parameter.with("message", desc));
	}

	public void publishOnAlbum(String albumId, InputStream is, String name, String desc) throws IOException {
		FacebookClient facebookClient = new DefaultFacebookClient(pageAccessToken, Version.LATEST);

		FacebookType photo = facebookClient.publish(pageID + "/" + albumId + "/photos" ,
				FacebookType.class,BinaryAttachment.with(name, is), Parameter.with("message", desc));
	}
	
}
