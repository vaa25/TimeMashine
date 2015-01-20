package org.noip.wizzardo.grabber.tags;

import org.noip.wizzardo.grabber.Util;

import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class Wm {
    private String language;
    private int found;
    private List<Place> places;
    private int page;
    private int count;
    private Debug debug;

    @Override
    public String toString() {
        return "Wm{" +
                "language='" + language + '\'' +
                ", \nfound=" + found +
                ", \nplaces=" + Util.toString(places) +
                ", \npage=" + page +
                ", \ncount=" + count +
                ", \ndebug=" + debug +
                '}';
    }

    public boolean isAvailable() {
        return (debug == null);
    }
    public List<Place> getPlaces() {
        return places;
    }
}
