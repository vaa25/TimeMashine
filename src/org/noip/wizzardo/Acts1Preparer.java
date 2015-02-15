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
    private String[][] data;

    public static void main(String[] args) {

        Text text = new Text(Utils.loadText("acts1.txt"));
        System.out.println(text.getIndexed());
        System.out.println(text.search("Иудее"));
        System.out.println(text.search("Самарии"));
        System.out.println(text.search("Елеон"));
        System.out.println(text.search("Акелдама"));
        System.out.println(text.search("Иерусалим"));
        System.out.println(text.search("Иерусалиму"));
        System.out.println(text.search("Иерусалиме"));
        System.out.println(text.search("Иерусалима"));
    }

    public List<Place> getPlaces() {
        placeDAO = new PlaceDAO(new DataBase().getStatement());
        places = new ArrayList<>();
        data = new String[][]{{"Город Давида", "ru", "Иерусалим"},
                {"Масличная гора", "ru", "Елеон (Масличная гора)"},
                {"Samaria", "en", "Самария"},
                {"Акелдама / Akeldama", "ru", "Акелдама"},
                {"Округ Иудея", "ru", "Иудея"}
        };
        addPlaces();
        return places;
    }

    private void addPlaces() {
        for (String[] strings : data) {
            System.out.println(strings[0]);
            addPlace(strings[0], strings[1]);
        }
    }

    public String getText() {
        return new TextProcessor().getText();
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

    private class TextProcessor {
        private Text text;

        public String getText() {
            text = new Text(Utils.loadText("acts1.txt"));
            setMetaTag();
            setPlaceTag(0, 64, "center");
            setPlaceTag(0, 139, "addBound");
            setPlaceTag(4, 143, "addBound");
            setPlaceTag(2, 145, "addBound");
            setPlaceTag(0, 216, "center");
            setPlaceTag(1, 220, "addBound");
            setPlaceTag(0, 224, "addBound");
            setPlaceTag(0, 346, "center");
            setPlaceTag(3, 356, "addBound");
            return text.toString();
        }

        private void setMetaTag() {
            Tag meta = new Tag("meta");
            meta.setAttribute("id", "meta");
            meta.setAttribute("size", String.valueOf(text.size()));
            meta.setAttribute("zoom", String.valueOf(9));
            meta.setAttribute("position", new Polygon(35, 32));
            text.setTag(meta);
        }

        private void setPlaceTag(int dataIndex, int id, String panTo) {
            Tag result = new Tag("place");
            result.setAttribute("placeName", data[dataIndex][0]);
            result.setAttribute("visualName", data[dataIndex][2]);
            result.setAttribute("panTo", panTo);
            text.setTag((result), id);
        }
    }
}
