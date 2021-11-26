package io.steamreviewbot.services;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class TwitterService {

    @Value("${consumer.key}")
    private String consumerKey;
    @Value("${consumer.secret}")
    private String consumerSecret;
    @Value("${auth.token}")
    private String accessToken;
    @Value("${auth.secret}")
    private String accessTokenSecret;

    private Twitter twitter;

    private void newTwConnection() {
        if (this.twitter == null) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(consumerKey)
                    .setOAuthConsumerSecret(consumerSecret)
                    .setOAuthAccessToken(accessToken)
                    .setOAuthAccessTokenSecret(accessTokenSecret);
            TwitterFactory tf = new TwitterFactory(cb.build());
            this.twitter = tf.getInstance();
        }
    }

    public long newTweet(InputStream imagefile, String statusMessage, String comment) throws IOException, TwitterException {
        if (twitter == null) {
            newTwConnection();
        }

        long[] mediaIds = new long[1];
        UploadedMedia media = twitter.uploadMedia(fileFromInputStream(imagefile));
        mediaIds[0] = media.getMediaId();

        StatusUpdate statusUpdate = new StatusUpdate(statusMessage);
        statusUpdate.setMediaIds(mediaIds);

        Status status = twitter.updateStatus(statusUpdate);

        long statusId = status.getId();
        commentOnTweet(comment, statusId);

        return statusId;
    }

    public void commentOnTweet(String msg, long tweetId) throws TwitterException {
        StatusUpdate statusReply = new StatusUpdate(msg);
        statusReply.setInReplyToStatusId(tweetId);

        twitter.updateStatus(statusReply);
    }

    private File fileFromInputStream(InputStream is) throws IOException {
        File tempFile = File.createTempFile("aux", ".jpg");
        tempFile.deleteOnExit();
        FileOutputStream out = new FileOutputStream(tempFile);
        IOUtils.copy(is, out);

        return tempFile;
    }
}