package com.mr_faton;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Description
 *
 * @author Mr_Faton
 * @version 1.0
 * @since 05.10.2015
 */
public class PostTweet {
    private static String consumerKey = "nGvA3vWivAiPyjYLa8jB4UoVc";
    private static String consumerSecret = "jGognAhbro7CLVKspYXH1JTWxEDSeejd0BmbhcdJXfYdnuDisS";
    private static String accessToken = "277175513-Y91OulkUdVKBwzxL0bmFVGmqPFBbUrnv3pwRHLZW";
    private static String accessTokenSecret = "CI51K1He67W7mC3rINmUqtIYe0xotBVLmNK3B8jS2pYoG";

    public static void main(String[] args) throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        twitter.updateStatus("Hi!");
    }
}
