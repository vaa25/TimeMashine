package org.noip.wizzardo.grabber;

import org.noip.wizzardo.grabber.tags.Polygon;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Б on 20.01.2015.
 */
public class WmPlaceSearchUrlBuilder {
    private String address = "";
    private String key = "";
    private String function = "";
    private String q = "";
    private String lat = "";
    private String lon = "";
    private String format = "";
    private String pack = "";
    private String language = "";
    private String page = "";
    private String count = "";
    private String category = "";
    private String categories_or = "";
    private String categories_and = "";
    private String distance = "";


    public WmPlaceSearchUrlBuilder() {
        address = "http://api.wikimapia.org/";
        key = "example";
        page = "1";
        count = "50";
        format = "json";
        function = "place.search";
    }

    public WmPlaceSearchUrlBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public WmPlaceSearchUrlBuilder setPolygon(Polygon polygon) {
        return setLat(polygon.getY()).setLon(polygon.getX());
    }

    public WmPlaceSearchUrlBuilder setLat(String lat) {
        if (isDouble(lat)) {
            this.lat = lat;
        }
        return this;
    }

    public WmPlaceSearchUrlBuilder setLat(double lat) {
        this.lat = String.valueOf(lat);
        return this;
    }

    public WmPlaceSearchUrlBuilder setLat(int lat) {
        this.lat = String.valueOf(lat);
        return this;
    }

    public WmPlaceSearchUrlBuilder setLon(String lon) {
        if (isDouble(lon)) {
            this.lon = lon;
        }
        return this;
    }

    private boolean isDouble(String lon) {
        try {
            Double.valueOf(lon);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public WmPlaceSearchUrlBuilder setLon(double lon) {
        this.lon = String.valueOf(lon);
        return this;
    }

    public WmPlaceSearchUrlBuilder setLon(int lon) {
        this.lon = String.valueOf(lon);
        return this;
    }

    public WmPlaceSearchUrlBuilder setQuery(String q) {
        try {
            this.q = URLEncoder.encode(q, "UTF-8").replaceAll(" ", "+");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    public WmPlaceSearchUrlBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }

    public WmPlaceSearchUrlBuilder setDistance(String distance) {
        this.distance = distance;
        return this;
    }

    public WmPlaceSearchUrlBuilder setDistance(int distance) {
        return setDistance((distance > 0) ? String.valueOf(distance) : "");
    }

    public WmPlaceSearchUrlBuilder setDistance(double distance) {
        return setDistance((distance > 0) ? String.valueOf(distance) : "");
    }

    public String build() {
        return new StringBuilder().append(address)
                .append("?key=").append(key)
                .append("&function=").append(function)
                .append("&q=").append(q)
                .append("&lat=").append(lat)
                .append("&lon=").append(lon)
                .append("&format=").append(format)
                .append("&pack=").append(pack)
                .append("&language=").append(language)
                .append("&page=").append(page)
                .append("&count=").append(count)
                .append("&category=").append(category)
                .append("&categories_or=").append(categories_or)
                .append("&categories_and=").append(categories_and)
                .append("&distance=").append(distance).toString();
    }

}
