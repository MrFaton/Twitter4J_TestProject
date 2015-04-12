package net.MrFaton.Testt;

import java.net.URLDecoder;
import java.util.Base64;

/**
 * Created by Mr_Faton on 11.04.2015.
 */
public class Test {
    public static void main(String[] args) {
        Base64.Decoder decoder = Base64.getDecoder();
        Base64.Encoder encoder = Base64.getEncoder();


        System.out.println(URLDecoder.decode("nPW8lRFAuDXRmLXrsmmwAeva3yG9IJPf"));


        System.out.println(encoder.encodeToString("rkTfI2x3xyinEhgf9EkyLHpXn".getBytes()));
    }
}
