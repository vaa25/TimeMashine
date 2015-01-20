package org.noip.wizzardo.grabber;

import com.google.gson.Gson;
import org.noip.wizzardo.grabber.tags.Wm;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class WmDownloader {
    private double latitude;
    private double longitude;
    private double distance;
    private String name;
    private String language = "en";
    private QueryBuilder builder = new QueryBuilder();

    public WmDownloader(String name) {
        this(31, 35, 0, name);
    }

    public WmDownloader(double latitude, double longitude, double distance, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.name = name;

    }

    public WmDownloader(double latitude, double longitude, double distance) {
        this(latitude, longitude, distance, "");
    }

    public WmDownloader(double latitude, double longitude, String name) {
        this(latitude, longitude, 0, name);
    }

    public WmDownloader(double latitude, double longitude) {
        this(latitude, longitude, 100, "");
    }

    private String getUrl() throws UnsupportedEncodingException {
        return "http://api.wikimapia.org/?" +
                "key=example" + //getMyKey()+
                "&function=place.search" +
                "&q=" + URLEncoder.encode(name, "UTF-8").replaceAll(" ", "+") +
                "&lat=" + latitude +
                "&lon=" + longitude +
                "&format=json" +
                "&pack=" +
                "&language=" + language +
                "&page=1" +
                "&count=50" +
                "&category=" +
                "&categories_or=" +
                "&categories_and=" +
                "&distance=" + ((distance > 0) ? distance : "");
    }

    private String getMyKey() {
        return "EDE1F443-8256FDD6-EACCDBEA-D17E8FF5-5239E7AD-411FE4C4-F6210DEE-BA2473D2";
    }

    private String getHTML(String urlToRead) throws UnsupportedEncodingException {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Wm downloadWm() throws UnsupportedEncodingException {
        Gson gson = new Gson();
        String json = getHTML(getUrl());
        Wm newWm = gson.fromJson(json, Wm.class);
        if (newWm.isAvailable()) {
            save(name + ".json", json);
        } else {
            json = load(name + ".json");
            newWm = gson.fromJson(json, Wm.class);
        }
        return newWm;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    private String toGson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    private String load(String file) {
        StringBuilder result = new StringBuilder();
        try {
            Path path = new File(file).toPath();
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF8"));
            for (String line : lines) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    private void save(String file, String value) {
        try {
            Path path = new File(file).toPath();
            if (!path.toFile().exists()) Files.createFile(path);
            Files.write(path, value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPlace(Wm wm) throws IllegalArgumentException {
        builder.setPlaceTitle(name);
        return toGson(builder.getPlace(wm));
    }
}
