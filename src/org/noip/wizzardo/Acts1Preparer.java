package org.noip.wizzardo;

import org.noip.wizzardo.db.DataBase;
import org.noip.wizzardo.db.tables.myObjects.PlaceDAO;
import org.noip.wizzardo.grabber.tags.Polygon;
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
        addPlace("Акелдама / Akeldama", "ru");

        return places;
    }

    public String getText() {
        Text text = new Text(Utils.loadText("acts1.txt"));

        Tag meta = new Tag("meta");
        meta.setAttribute("id", "meta");
        meta.setAttribute("size", String.valueOf(text.size()));
        meta.setAttribute("zoom", String.valueOf(9));
        meta.setAttribute("position", new Polygon(35, 32));
        text.setTag(meta);

        text.setTag(getPlaceTag("Город Давида", "Иерусалим"), 216);
        text.setTag(getPlaceTag("Масличная гора", "Елеон (Масличная гора)"), 220);
        text.setTag(getPlaceTag("Акелдама / Akeldama", "Акелдама"), 356);
        return text.toString();
    }

    private Tag getPlaceTag(String placeName, String visualName) {
        Tag result = new Tag("place");
        result.setAttribute("placeName", placeName);
        result.setAttribute("visualName", visualName);
        return result;
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
