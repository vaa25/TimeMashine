package org.noip.wizzardo.grabber;

import org.noip.wizzardo.grabber.tags.Place;
import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.grabber.tags.Wm;

import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class QueryBuilder {
    private String placeTitle;

    public org.noip.wizzardo.coords.Place getPlace(Wm wm) throws IllegalArgumentException {
        Place place = selectPlace(wm);
        String title = placeTitle;
//        try {
//            title= URLEncoder.encode(placeTitle, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        return new org.noip.wizzardo.coords.Place(place.getPolygons(), place.getLocationCenter(), title);
    }

    public List<Polygon> getPolygons(Wm wm) throws IllegalArgumentException {
        return selectPlace(wm).getPolygons();
    }

    public Polygon getLocationCenter(Wm wm) throws IllegalArgumentException {
        return selectPlace(wm).getLocationCenter();
    }

    private Place selectPlace(Wm wm) throws IllegalArgumentException {
        List<Place> places = wm.getPlaces();
        Place result = null;
        boolean foundOne = false;
        for (Place place : places) {
            if (checkPlaceTitle(place)) {
                if (foundOne) {
                    throw new IllegalArgumentException("Not unique placeTitle '" + placeTitle + '\'');
                }
                result = place;
                foundOne = true;
            }
        }
        if (!foundOne) {
            throw new IllegalArgumentException("No one placeTitle equals '" + placeTitle + '\'');
        }
        return result;
    }

    private boolean checkPlaceTitle(Place place) {
        return !"".equals(placeTitle) && placeTitle.equals(place.getTitle());
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

}
