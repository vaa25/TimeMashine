package org.noip.wizzardo.grabber;

import com.google.gson.Gson;
import org.noip.wizzardo.coords.Place;

import java.io.UnsupportedEncodingException;

/**
 * Created by Б on 19.01.2015.
 */
public class Tester {
    public static void main(String[] args) {
        try {
            String title = "Город Давида";
            PolygonGrabber grabber = new PolygonGrabber(title);
            grabber.setLanguage("ru");
            grabber.downloadWm();
            String place = grabber.getPlace();
            System.out.println(place);
            Gson gson = new Gson();
            System.out.println(gson.fromJson(place, Place.class));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
