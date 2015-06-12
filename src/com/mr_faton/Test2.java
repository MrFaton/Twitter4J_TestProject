package com.mr_faton;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by Mr_Faton on 26.03.2015.
 */
public class Test2 {
    private static String user = "Mr_Faton";
    private static String consumerKey = "rkTfI2x3xyinEhgf9EkyLHpXn";
    private static String consumerSecret = "wBvno67v6r7YZCLUbR2MK3DUI2rvtfc082IWPC5xE4rHgfHMpg";
    private static String accessToken = "KiUjchyOd2wgKBpwE8SN0ROVRGw2WX0AZt8WO1WF";
    private static String accessTokenSecret = "92zAGkJPbhmsvgRrUnABoIojHj2p15Wdvpmv88li8k7aI";

    public static void main(String[] args) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(consumerKey);
        configurationBuilder.setOAuthConsumerSecret(consumerSecret);
        configurationBuilder.setOAuthAccessToken(accessToken);
        configurationBuilder.setOAuthAccessTokenSecret(accessTokenSecret);

        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter twitter = twitterFactory.getInstance();

        try {
            System.out.println(twitter.getScreenName());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
