package net.MrFaton;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Mr_Faton on 26.03.2015.
 */
public class Test3 {
    private static String user = "Mr_Faton";
    private static String consumerKey = "rkTfI2x3xyinEhgf9EkyLHpXn";
    private static String consumerSecret = "wBvno67v6r7YZCLUbR2MK3DUI2rvtfc082IWPC5xE4rHgfHMpg";
    private static String accessToken = "KiUjchyOd2wgKBpwE8SN0ROVRGw2WX0AZt8WO1WF";
    private static String accessTokenSecret = "92zAGkJPbhmsvgRrUnABoIojHj2p15Wdvpmv88li8k7aI";

    public static void main(String[] args) {
        args = new String[] {"Hi all"};
        if (args.length < 1) {
            System.out.println("Usage: java twitter4j.examples.tweets.UpdateStatus [text]");
            System.exit(-1);
        }
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            try {
                // get request token.
                // this will throw IllegalStateException if access token is already available
                RequestToken requestToken = twitter.getOAuthRequestToken();
                System.out.println("Got request token.");
                System.out.println("Request token: " + requestToken.getToken());
                System.out.println("Request token secret: " + requestToken.getTokenSecret());
                AccessToken accessToken = null;

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while (null == accessToken) {
                    System.out.println("Open the following URL and grant access to your account:");
                    System.out.println(requestToken.getAuthorizationURL());
                    System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
                    String pin = br.readLine();
                    try {
                        if (pin.length() > 0) {
                            accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                        } else {
                            accessToken = twitter.getOAuthAccessToken(requestToken);
                        }
                    } catch (TwitterException te) {
                        if (401 == te.getStatusCode()) {
                            System.out.println("Unable to get the access token.");
                        } else {
                            te.printStackTrace();
                        }
                    }
                }
                System.out.println("Got access token.");
                System.out.println("Access token: " + accessToken.getToken());
                System.out.println("Access token secret: " + accessToken.getTokenSecret());
            } catch (IllegalStateException ie) {
                // access token is already available, or consumer key/secret is not set.
                if (!twitter.getAuthorization().isEnabled()) {
                    System.out.println("OAuth consumer key/secret is not set.");
                    System.exit(-1);
                }
            }
            Status status = twitter.updateStatus(args[0]);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to read the system input.");
            System.exit(-1);
        }
    }
}
