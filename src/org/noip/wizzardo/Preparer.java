package org.noip.wizzardo;

import org.noip.wizzardo.coords.Babylon;
import org.noip.wizzardo.coords.Edem;
import org.noip.wizzardo.coords.Edem2;
import org.noip.wizzardo.db.Crud;
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
    private Crud crud = new Crud();
    public List<Place> getPlaces() {

        places = new ArrayList<>();
        addPlace(new Babylon());
        addPlace(new Edem2());
        addPlace(new Edem());
        addPlace("Город Давида", "ru");
        addPlace("Масличная гора", "ru");
        addPlace("Garden of Gethsemane", "en");
        addPlace("Mount Zion", "en");
        return places;
    }

    private List<Place> addPlace(Place place) {
        if (!crud.hasPlace(place.getTitle())) {
            crud.setPlace(place);
        }
        places.add(place);
        return places;
    }

    private List<Place> addPlace(String title, String language) {
        Place place = null;
        if (!crud.hasPlace(title)) {
            place = downloadPlace(title, language);
            crud.setPlace(place);
        } else {
            place = crud.getPlace(title);
        }
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
