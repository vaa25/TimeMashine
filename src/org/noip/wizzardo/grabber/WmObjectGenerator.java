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

    public org.noip.wizzardo.objects.Place getPlace() throws IllegalArgumentException {
        Place place = selectPlace();
        return new org.noip.wizzardo.objects.Place(place.getPolygons(), place.getLocationCenter(), place.getTitle());
    }

    private Place selectPlace() throws IllegalArgumentException {
        checkWmValidity();
        return new PlaceSelector().invoke().getResult();
    }

    private void checkWmValidity() {
        if (wm == null) {
            throw new IllegalArgumentException("Wm is null");
        } else if (!wm.isAvailable()) {
            throw new IllegalArgumentException(wm.getMessage());
        }
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    private class PlaceSelector {
        private Place result;
        private boolean foundOne;

        public Place getResult() {
            return result;
        }

        public PlaceSelector invoke() {
            result = null;
            foundOne = false;
            selectPlace();
            checkResult();
            return this;
        }

        private void selectPlace() {
            for (Place place : wm.getPlaces()) {
                checkPlaceEligible(place);
            }
        }

        private void checkPlaceEligible(Place place) {
            if (checkPlaceTitle(place)) {
                checkUnique();
                result = place;
                foundOne = true;
            }
        }

        private void checkUnique() {
            if (foundOne) {
                throw new IllegalArgumentException("Not unique placeTitle '" + placeTitle + '\'');
            }
        }

        private void checkResult() {
            if (!foundOne) {
                throw new IllegalArgumentException("No one placeTitle equals '" + placeTitle + '\'');
            }
        }

        private boolean checkPlaceTitle(Place place) {
            return !"".equals(placeTitle) && placeTitle.equals(place.getTitle());
        }
    }
}
