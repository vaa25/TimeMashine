package org.noip.wizzardo.grabber;

import com.google.gson.Gson;
import org.noip.wizzardo.objects.Place;

/**
 * Created by Б on 19.01.2015.
 */
public class Tester {
    public static void main(String[] args) {
        String title = "Город Давида";
        test(title);
        title = "Масличная гора";
        test(title);
    }

    private static void test(String title) {
        try {
            WmDownloader grabber = new WmDownloader(title);
            grabber.setLanguage("ru");
            WmObjectGenerator builder = new WmObjectGenerator(grabber.downloadWm());
            builder.setPlaceTitle(title);
            Place place = builder.getPlace();
            System.out.println(place);
            Gson gson = new Gson();
            System.out.println(gson.toJson(place));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
