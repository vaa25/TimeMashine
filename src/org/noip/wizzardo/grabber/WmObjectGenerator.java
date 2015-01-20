package org.noip.wizzardo.grabber;

import org.noip.wizzardo.grabber.tags.Place;
import org.noip.wizzardo.grabber.tags.Wm;

/**
 * Created by Б on 18.01.2015.
 */
public class WmObjectGenerator {
    private String placeTitle;
    private Wm wm;

    public WmObjectGenerator(Wm wm) {
        this.wm = wm;
    }

    public org.noip.wizzardo.coords.Place getPlace() throws IllegalArgumentException {
        Place place = selectPlace();
        return new org.noip.wizzardo.coords.Place(place.getPolygons(), place.getLocationCenter(), place.getTitle());
    }

    private Place selectPlace() throws IllegalArgumentException {
        Place result = null;
        boolean foundOne = false;
        for (Place place : wm.getPlaces()) {
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
