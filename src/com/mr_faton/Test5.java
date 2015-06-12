package com.mr_faton;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Mr_Faton on 12.04.2015.
 */
public class Test5 {
    private static String consumerKey = "nGvA3vWivAiPyjYLa8jB4UoVc";
    private static String consumerSecret = "jGognAhbro7CLVKspYXH1JTWxEDSeejd0BmbhcdJXfYdnuDisS";
    public static void main(String[] args) {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        try {
            RequestToken requestToken = twitter.getOAuthRequestToken();
            System.out.println("Request token: " + requestToken.getToken());
            System.out.println("Request token secret: " + requestToken.getTokenSecret());
            System.out.println("getAuthorizationURL " + requestToken.getAuthorizationURL());

            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
            String pin = br.readLine();

            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
            System.out.println("Access token: " + accessToken.getToken());
            System.out.println("Access token secret: " + accessToken.getTokenSecret());
        } catch (TwitterException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
/*
более краткий способ получить Access token и Access token secret. Но лучше из Test4, но только чуть чуть его переделать
 */