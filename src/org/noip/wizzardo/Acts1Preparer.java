package org.noip.wizzardo;

import org.noip.wizzardo.db.DataBase;
import org.noip.wizzardo.db.tables.myObjects.PlaceDAO;
import org.noip.wizzardo.grabber.utils.GrabberUtil;
import org.noip.wizzardo.objects.Place;
import org.noip.wizzardo.objects.Text;
import org.noip.wizzardo.objects.tags.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Б on 21.01.2015.
 */
public class Acts1Preparer {
    private List<Place> places;
    private PlaceDAO placeDAO;

    public List<Place> getPlaces() {
        placeDAO = new PlaceDAO(new DataBase().getStatement());
        places = new ArrayList<>();
        addPlace("Масличная гора", "ru");
        addPlace("Город Давида", "ru");

        return places;
    }

    public String getText() {
        Text text = new Text(Utils.loadText("acts1.txt"));

        Tag yerusalemTag = new Tag("place");
        yerusalemTag.setAttribute("placeName", "Город Давида");
        yerusalemTag.setAttribute("visualName", "Иерусалим");
        text.setTag(yerusalemTag, 214);

        Tag eleonTag = new Tag("place");
        eleonTag.setAttribute("placeName", "Масличная гора");
        eleonTag.setAttribute("visualName", "Масличная гора");
        text.setTag(eleonTag, 216, 218);
        return text.toString();
    }

    private List<Place> addPlace(Place place) {
        if (!placeDAO.hasPlace(place.getTitle())) {
            placeDAO.create(place);
        }
        places.add(place);
        return places;
    }

    private List<Place> addPlace(String title, String language) {
        Place place;
        if (!placeDAO.hasPlace(title)) {
            place = GrabberUtil.downloadPlace(title, language);
            placeDAO.create(place);
        } else {
            place = placeDAO.read(title);
        }
        if (place != null) {
            places.add(place);
        }
        return places;
    }
}
