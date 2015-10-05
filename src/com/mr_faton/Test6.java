package com.mr_faton;

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
            ResponseList<Status> homeTimeline = twitter.getUserTimeline(new Paging(1, 1));
            Status lastStatus = homeTimeline.get(0);
            String text = lastStatus.getText();
            System.out.println(text);

            Paging pages = new Paging(1, 5);//первая пачка из 5 штук
            System.out.println(pages);
//            Paging pages = new Paging(2, 5);//вторая пачка из 5 штук
//            Paging pages = new Paging(1, 10);//предыдущие 2 строки можно было заменить так
            ResponseList<Status> timeLine = twitter.getUserTimeline("katherine_27m", pages);
            for (Status status : timeLine) {
                System.out.println("name = " + status.getUser().getName());
                System.out.println("lang = " + status.getLang());
                System.out.println("date = " + status.getCreatedAt());
                System.out.println("is retweet = " + status.isRetweet());
                System.out.println("tweet id = " + status.getId());
                System.out.println("text = " + status.getText());
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(status.getCreatedAt());
                System.out.println("calendar = " + String.format("%tY - %<tm - %<td", calendar));
                calendar.add(Calendar.YEAR, -2);
                System.out.println("calendar 2 years late" + String.format("%tY - %<tm - %<td", calendar));
                GregorianCalendar todayCalendar = new GregorianCalendar();
                todayCalendar.after(calendar);

                System.out.println("*****************************************\n");
            }
            System.out.println("All limits:");
            Map<String, RateLimitStatus> rateLimitStatusMap = twitter.getRateLimitStatus();
            for (Map.Entry<String, RateLimitStatus> entry : rateLimitStatusMap.entrySet()) {
                System.out.println("key=" + entry.getKey());
                System.out.println("value=" + entry.getValue());
            }
            System.out.println("*****************************************\n");
            System.out.println("Important limits:");

            RateLimitStatus applicationRateLimitStatus = rateLimitStatusMap.get("/application/rate_limit_status");
            printImportantLimit("Application rate limit status:", applicationRateLimitStatus);

            RateLimitStatus userTimeLineStatus = rateLimitStatusMap.get("/statuses/user_timeline");
            printImportantLimit("User time line status: ", userTimeLineStatus);

        } catch (TwitterException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static void printImportantLimit(String limitName, RateLimitStatus rateLimitStatus) {
        System.out.println(limitName);
        System.out.println("limit is: " + rateLimitStatus.getLimit());
        System.out.println("remaining is: " + rateLimitStatus.getRemaining());
        System.out.println("seconds until reset: " + rateLimitStatus.getSecondsUntilReset());
        System.out.println("reset time in seconds" + rateLimitStatus.getResetTimeInSeconds());
    }
}