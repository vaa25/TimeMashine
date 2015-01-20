package org.noip.wizzardo.grabber;

import org.noip.wizzardo.grabber.tags.Place;
import org.noip.wizzardo.grabber.tags.Wm;

import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class WmObjectBuilder {
    private String placeTitle;

    public org.noip.wizzardo.coords.Place getPlace(Wm wm) throws IllegalArgumentException {
        Place place = selectPlace(wm);
        return new org.noip.wizzardo.coords.Place(place.getPolygons(), place.getLocationCenter(), placeTitle);
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
