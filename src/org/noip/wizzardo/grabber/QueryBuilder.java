package org.noip.wizzardo.grabber;

import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class QueryBuilder {
    private String placeTitle;
    private boolean error = false;

    public org.noip.wizzardo.coords.Place getPlace(Wm wm) throws IllegalArgumentException {
        Place place = selectPlace(wm);
        return new org.noip.wizzardo.coords.Place(
                place.getPolygons(), place.getLocationCenter(), placeTitle
        );
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
        for (Place place : places) {
            if (checkPlaceTitle(place)) {
                if (error) {
                    throw new IllegalArgumentException("Not unique placeTitle '" + placeTitle + '\'');
                }
                result = place;
                error = true;
            }
        }
        if (!error) {
            throw new IllegalArgumentException("No one placeTitle equals '" + placeTitle + '\'');
        }
        error = false;
        return result;
    }

    private boolean checkPlaceTitle(Place place) {
        return !"".equals(placeTitle) && placeTitle.equals(place.getTitle());
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

}
