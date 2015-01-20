package org.noip.wizzardo.grabber;

import com.google.gson.Gson;
import org.noip.wizzardo.coords.Place;
import org.noip.wizzardo.grabber.tags.Wm;

import java.nio.file.NoSuchFileException;

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
            Wm wm = grabber.downloadWm();
            WmObjectBuilder builder = new WmObjectBuilder();
            builder.setPlaceTitle(title);
            Place place = builder.getPlace(wm);
            System.out.println(place);
            Gson gson = new Gson();
            System.out.println(gson.toJson(place));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        }
    }

}
