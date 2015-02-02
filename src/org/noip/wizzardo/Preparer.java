package org.noip.wizzardo;

import org.noip.wizzardo.coords.*;
import org.noip.wizzardo.db.DataBase;
import org.noip.wizzardo.db.tables.myObjects.PlaceDAO;
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
    private PlaceDAO tbPlace;
    public List<Place> getPlaces() {
        tbPlace = new PlaceDAO(new DataBase().getStatement());
        places = new ArrayList<>();
        addPlace(new Nazareth());
        addPlace(new Kana());
        addPlace(new VifaniaJordan());
        addPlace(new Kapernaum());
        addPlace(new Babylon());
        addPlace(new Edem2());
        addPlace(new Edem());
        addPlace("Вифсаида", "ru");
        addPlace("Город Давида", "ru");
        addPlace("Масличная гора", "ru");
        addPlace("Garden of Gethsemane", "en");
        addPlace("Mount Zion", "en");
        return places;
    }

    private List<Place> addPlace(Place place) {
        if (!tbPlace.hasPlace(place.getTitle())) {
            tbPlace.create(place);
        }
        places.add(place);
        return places;
    }

    private List<Place> addPlace(String title, String language) {
        Place place = null;
        if (!tbPlace.hasPlace(title)) {
            place = downloadPlace(title, language);
            tbPlace.create(place);
        } else {
            place = tbPlace.read(title);
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
        return generator.getPlace();
    }
}
