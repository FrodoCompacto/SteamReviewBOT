package io.steamreviewbot.services;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import com.restfb.types.GraphResponse;

@Service
public class FacebookService {
	
	@Value("${facebook.pageacess.token}")
    private String pageAccessToken;
	
	String pageID = "102746451339651";
	
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
	
}
