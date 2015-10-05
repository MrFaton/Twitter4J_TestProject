package com.mr_faton;

import twitter4j.*;
import twitter4j.auth.AccessToken;

/**
 * Created by Mr_Faton on 26.03.2015.
 */
public class Test {
    private static String consumerKey = "rkTfI2x3xyinEhgf9EkyLHpXn";
    private static String consumerSecret = "wBvno67v6r7YZCLUbR2MK3DUI2rvtfc082IWPC5xE4rHgfHMpg";
    private static String accessToken = "277175513-KiUjchyOd2wgKBpwE8SN0ROVRGw2WX0AZt8WO1WF";
    private static String accessTokenSecret = "92zAGkJPbhmsvgRrUnABoIojHj2p15Wdvpmv88li8k7aI";

    public static void main(String[] args) throws InterruptedException {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
        
        String text = "На улице уже солнце";
        try {
//            StatusUpdate statusUpdate = new StatusUpdate(text);
//            twitter.updateStatus(statusUpdate);

            Status status = twitter.updateStatus(text);

            System.out.println("статус обновлён: " + status.getText());

            Thread.currentThread().sleep(10000);

            twitter.destroyStatus(status.getId());
            System.out.println("статус удалён");

        } catch (TwitterException e) {
            System.err.println("НЕ могу обновить статус");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
/*
обновить статус в твиттере если нам доступны все кулючи
 */
