package org.noip.wizzardo.grabber.utils;

import org.noip.wizzardo.grabber.WmDownloader;
import org.noip.wizzardo.grabber.WmObjectGenerator;
import org.noip.wizzardo.objects.Place;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class GrabberUtil {
    public static String toString(List list) {
        if (list == null) {
            return "absent";
        }
        StringBuilder result = new StringBuilder("List has ").append(list.size()).append(" elements\n");
        for (Object elem : list) {
            result.append('\t').append(elem.toString()).append('\n');
        }
        return result.append("End of list").toString();
    }

    public static String download(String urlToRead) {
        StringBuilder result = new StringBuilder();
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(urlToRead).openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static Place downloadPlace(String title, String language) {
        WmDownloader wmDownloader = new WmDownloader(title);
        wmDownloader.setLanguage(language);
        WmObjectGenerator generator = new WmObjectGenerator(wmDownloader.downloadWm());
        generator.setPlaceTitle(title);
        return generator.getPlace();
    }
}
