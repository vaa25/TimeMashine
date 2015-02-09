package org.noip.wizzardo.objects;

import org.noip.wizzardo.grabber.tags.Polygon;

import java.util.List;

/**
 * Created by Б on 19.01.2015.
 */
public class Place {
    protected List<Polygon> bound;
    protected Polygon center;
    private String title;

    public Place(List<Polygon> bound, Polygon center, String title) {
        this.bound = bound;
        this.center = center;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Place{" +
                "polygons=" + org.noip.wizzardo.grabber.utils.Util.toString(bound) +
                ", \ncenter=" + center +
                ", \ntitle='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public Polygon getCenter() {
        return center;
    }

    public List<Polygon> getBound() {
        return bound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Place place = (Place) o;

        return center.equals(place.center) && title.equals(place.title);

    }

    @Override
    public int hashCode() {
        int result = center.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }
}
