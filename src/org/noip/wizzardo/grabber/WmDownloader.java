package org.noip.wizzardo.grabber;

import com.google.gson.Gson;
import org.noip.wizzardo.grabber.tags.Wm;
import org.noip.wizzardo.grabber.utils.GrabberUtil;

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
        return new Gson().fromJson(GrabberUtil.download(getUrl()), Wm.class);
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
