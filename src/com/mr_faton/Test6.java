package com.mr_faton;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.Map;

/**
 * Created by Mr_Faton on 26.03.2015.
 */
public class Test6 {
    private static String user = "Mr_Faton";
    private static String consumerKey = "nGvA3vWivAiPyjYLa8jB4UoVc";
    private static String consumerSecret = "jGognAhbro7CLVKspYXH1JTWxEDSeejd0BmbhcdJXfYdnuDisS";
    private static String accessToken = "277175513-Y91OulkUdVKBwzxL0bmFVGmqPFBbUrnv3pwRHLZW";
    private static String accessTokenSecret = "CI51K1He67W7mC3rINmUqtIYe0xotBVLmNK3B8jS2pYoG";

    public static void main(String[] args) throws InterruptedException {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
        
        try {
            Paging pages = new Paging(1, 5);//первая пачка из 5 штук
//            Paging pages = new Paging(2, 5);//вторая пачка из 5 штук
//            Paging pages = new Paging(1, 10);//предыдущие 2 строки можно было заменить так
            ResponseList<Status> timeLine = twitter.getUserTimeline("Wild_Happiness", pages);
            for (Status status : timeLine) {
                System.out.println("name = " + status.getUser().getName());
                System.out.println("lang = " + status.getLang());
                System.out.println("date = " + status.getCreatedAt());
                System.out.println("is retweet = " + status.isRetweet());
                System.out.println("tweet id = " + status.getId());
                System.out.println("text = " + status.getText());
                System.out.println("*****************************************");
            }

            Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus();
            for (Map.Entry<String, RateLimitStatus> entry : rateLimitStatus.entrySet()) {
                System.out.println("key=" + entry.getKey());
                System.out.println("value=" + entry.getValue());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}