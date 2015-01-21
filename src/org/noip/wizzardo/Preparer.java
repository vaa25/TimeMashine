package org.noip.wizzardo;

import org.noip.wizzardo.coords.Babylon;
import org.noip.wizzardo.coords.Edem;
import org.noip.wizzardo.coords.Edem2;
import org.noip.wizzardo.grabber.WmDownloader;
import org.noip.wizzardo.grabber.WmObjectGenerator;
import org.noip.wizzardo.objects.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Б on 21.01.2015.
 */
public class Preparer {
    private List<Place> places;

    public List<Place> getPlaces() {
        places = new ArrayList<>();
        places.add(new Babylon());
        places.add(new Edem2());
        places.add(new Edem());
        addPlace("Город Давида", "ru");
        addPlace("Масличная гора", "ru");
        addPlace("Garden of Gethsemane", "en");
        addPlace("Mount Zion", "en");
        changeTitle("Garden of Gethsemane", "Гефсиманский сад");
        changeTitle("Mount Zion", "Гора Сион");
        return places;
    }

    private void changeTitle(String oldTitle, String newTitle) {
        Place place = selectByTitle(oldTitle);
        if (place != null) {
            place.setTitle(newTitle);
        }
    }

    private Place selectByTitle(String title) {
        for (Place place : places) {
            if (place.getTitle().equals(title)) {
                return place;
            }
        }
        return null;
    }

    private List<Place> addPlace(String title, String language) {
        Place place = downloadPlace(title, language);
        if (place != null) {
            places.add(place);
        }
        return places;
    }

    private Place downloadPlace(String title, String language) {
        WmDownloader grabber = new WmDownloader(title);
        grabber.setLanguage(language);
        WmObjectGenerator generator = new WmObjectGenerator(grabber.downloadWm());
        generator.setPlaceTitle(title);
        try {
            return (generator.getPlace());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
