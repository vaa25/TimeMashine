package org.noip.wizardo.grabber;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class QueryBuilder {
    private String placeTitle;
    private boolean error = false;

    public org.noip.wizardo.coords.Place getPlace(Wm wm) throws InvalidArgumentException {
        Place place = selectPlace(wm);
        return new org.noip.wizardo.coords.Place(
                place.getPolygons(), place.getLocationCenter(), placeTitle
        );
    }

    public List<Polygon> getPolygons(Wm wm) throws InvalidArgumentException {
        return selectPlace(wm).getPolygons();
    }

    public Polygon getLocationCenter(Wm wm) throws InvalidArgumentException {
        return selectPlace(wm).getLocationCenter();
    }

    private Place selectPlace(Wm wm) throws InvalidArgumentException {
        List<Place> places = wm.getPlaces();
        Place result = null;
        for (Place place : places) {
            if (checkPlaceTitle(place)) {
                if (error) {
                    throw new InvalidArgumentException(new String[]{"Not unique placeTitle '" + placeTitle + '\''});
                }
                result = place;
                error = true;
            }
        }
        if (!error) {
            throw new InvalidArgumentException(new String[]{"No one placeTitle equals '" + placeTitle + '\''});
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
