package org.noip.wizzardo.coords;

import org.noip.wizzardo.grabber.tags.Polygon;

import java.util.List;

/**
 * Created by Б on 19.01.2015.
 */
public class Place {
    private List<Polygon> polygons;
    private Polygon center;
    private String title;

    public Place(List<Polygon> polygons, Polygon center, String title) {
        this.polygons = polygons;
        this.center = center;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Place{" +
                "polygons=" + org.noip.wizzardo.grabber.Util.toString(polygons) +
                ", \ncenter=" + center +
                ", \ntitle='" + title + '\'' +
                '}';
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public Polygon getCenter() {
        return center;
    }

    public String getTitle() {
        return title;
    }
}
