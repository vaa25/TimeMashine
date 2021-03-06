package org.noip.wizzardo.grabber.tags;

import org.noip.wizzardo.grabber.utils.GrabberUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by Б on 18.01.2015.
 */
public class Place {
    private int id;
    private int language_id;
    private String language_iso;
    private String urlhtml;
    private List<Tag> tags;
    private String title;
    private int distance;
    private String description;
    private String wikipedia;
    private boolean is_building;
    private boolean is_region;
    private boolean is_deleted;
    private int parent_id;
    private List<Polygon> polygon;
    private EditInfo edit_info;
    private boolean is_protected;
    private List<Photo> photos;
    private List<Comment> comments;
    private Location location;
    private Map<String, Language> availableLanguages;

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", \nlanguage_id=" + language_id +
                ", \nlanguage_iso='" + language_iso + '\'' +
                ", \nurlhtml='" + urlhtml + '\'' +
                ", \ntags='" + GrabberUtil.toString(tags) +
                ", \ntitle='" + title + '\'' +
                ", \ndistance=" + distance +
                ", \ndescription'=" + description + '\'' +
                ", \nwikipedia'=" + wikipedia + '\'' +
                ", \nis_building=" + is_building +
                ", \nis_region=" + is_region +
                ", \nis_deleted=" + is_deleted +
                ", \nparent_id=" + parent_id +
                ", \npolygon=" + GrabberUtil.toString(polygon) +
                ", \nedit_info=" + edit_info +
                ", \nis_protected=" + is_protected +
                ", \nphotos=" + GrabberUtil.toString(photos) +
                ", \ncomments=" + GrabberUtil.toString(comments) +
                ", \nlocation=" + location +
//                ", \navailableLanguages=" + GrabberUtil.toString(availableLanguages)+
                '}';
    }

    public String getTitle() {
        return title;
    }

    public List<Polygon> getPolygons() {
        return polygon;
    }

    public Polygon getLocationCenter() {
        return location.getCenter();
    }
}
