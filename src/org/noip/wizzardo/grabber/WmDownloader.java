package org.noip.wizzardo.grabber;

import com.google.gson.Gson;
import org.noip.wizzardo.grabber.tags.Wm;
import org.noip.wizzardo.grabber.utils.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Б on 18.01.2015.
 */
public class WmDownloader {
    private double latitude;
    private double longitude;
    private double distance;
    private String name;
    private String language = "en";

    public WmDownloader(String name) {
        this(31, 35, 0, name);
    }

    public WmDownloader(double latitude, double longitude, double distance, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.name = name;

    }

    private String getUrl() {
        return new WmPlaceSearchUrlBuilder()
                .setQuery(name)
                .setLat(latitude)
                .setLon(longitude)
                .setLanguage(language)
                .setDistance(distance)
                .setKey(getMyKey())
                .build();
    }

    private String getMyKey() {
        return "EDE1F443-8256FDD6-EACCDBEA-D17E8FF5-5239E7AD-411FE4C4-F6210DEE-BA2473D2";
    }

    public Wm downloadWm() {
        Path jsonFilePath = new File(name + ".json").toPath();
        String jsonData = getJson(jsonFilePath);
        Gson gson = new Gson();
        Wm newWm = gson.fromJson(jsonData, Wm.class);
        if (Files.notExists(jsonFilePath) && newWm != null && newWm.isAvailable()) {
            Util.save(jsonFilePath, jsonData);
        }
        return newWm;
    }

    private String getJson(Path jsonFilePath) {
        if (Files.exists(jsonFilePath)) {
            try {
                return Util.load(jsonFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Util.download(getUrl());
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
