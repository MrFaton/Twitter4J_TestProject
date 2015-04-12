package net.MrFaton;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.Map;

/**
 * Created by Mr_Faton on 26.03.2015.
 */
public class Test3 {
    private static String consumerKey = "rkTfI2x3xyinEhgf9EkyLHpXn";
    private static String consumerSecret = "wBvno67v6r7YZCLUbR2MK3DUI2rvtfc082IWPC5xE4rHgfHMpg";
    private static String accessToken = "277175513-KiUjchyOd2wgKBpwE8SN0ROVRGw2WX0AZt8WO1WF";
    private static String accessTokenSecret = "92zAGkJPbhmsvgRrUnABoIojHj2p15Wdvpmv88li8k7aI";

    public static void main(String[] args) throws InterruptedException {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        try {
            long cursor = -1;
            IDs ids;
            for (int i = 0; i < 2; i++) {
                showLimits(twitter);
                ids = twitter.getFollowersIDs(cursor);
                for (long id : ids.getIDs()) {
                    System.out.println(id);
                }
                System.out.println(ids.getNextCursor());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void showLimits(Twitter twitter) {
        try {
            Map<String ,RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus();
            for (String endpoint : rateLimitStatus.keySet()) {
                RateLimitStatus status = rateLimitStatus.get(endpoint);
                System.out.println("Endpoint: " + endpoint);
                System.out.println(" Limit: " + status.getLimit());
                System.out.println(" Remaining: " + status.getRemaining());
                System.out.println(" ResetTimeInSeconds: " + status.getResetTimeInSeconds());
                System.out.println(" SecondsUntilReset: " + status.getSecondsUntilReset());
                System.out.println("*************************************");
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get rate limit status: " + te.getMessage());
        }
    }
}
