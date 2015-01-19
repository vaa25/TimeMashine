package org.noip.wizzardo.grabber;

import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class Location {
    private double lon;
    private double lat;
    private double north;
    private double south;
    private double east;
    private double west;
    private String country;
    private String state;
    private String place;
    private int country_adm_id;
    private List<Gadm> gadm;
    private int city_id;
    private String city;
    private int zoom;

    @Override
    public String toString() {
        return "Location{" +
                "lon=" + lon +
                ", \nlat=" + lat +
                ", \nnorth=" + north +
                ", \nsouth=" + south +
                ", \neast=" + east +
                ", \nwest=" + west +
                ", \ncountry='" + country + '\'' +
                ", \nstate='" + state + '\'' +
                ", \nplace='" + place + '\'' +
                ", \ncountry_adm_id=" + country_adm_id +
                ", \ngadm=" + gadm +
                ", \ncity_id=" + city_id +
                ", \ncity='" + city + '\'' +
                ", \nzoom=" + zoom +
                '}';
    }

    public Polygon getCenter() {
        return new Polygon(lat, lon);
    }
}
