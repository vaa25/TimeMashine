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
public class Acts2Preparer {
    private List<Place> places;
    private PlaceDAO placeDAO;
    private String[][] data;

    public static void main(String[] args) {
        Text text = new Text(Utils.loadText("acts2.txt"));
        System.out.println(text.getIndexed());
        System.out.println(text.search("Иудеи"));
        System.out.println(text.search("Галилеяне"));
        System.out.println(text.search("Парфяне"));
        System.out.println(text.search("Мидяне"));
        System.out.println(text.search("Еламиты"));
        System.out.println(text.search("Месопотамии"));
        System.out.println(text.search("Иерусалиме"));
        System.out.println(text.search("Каппадокии"));
        System.out.println(text.search("Понта"));
        System.out.println(text.search("Асии"));
        System.out.println(text.search("Фригии"));
        System.out.println(text.search("Памфилии"));
        System.out.println(text.search("Египта"));
        System.out.println(text.search("Ливии"));
        System.out.println(text.search("Киринее"));
        System.out.println(text.search("Рима"));
        System.out.println(text.search("критяне"));
        System.out.println(text.search("аравитяне"));

    }

    public List<Place> getPlaces() {
        placeDAO = new PlaceDAO(new DataBase().getStatement());
        places = new ArrayList<>();
        data = new String[][]{{"Город Давида", "ru", "Иерусалим"}
                , {"Округ Иудея", "ru", "Иудея"}
        };
        for (String[] strings : data) {
            System.out.println(strings[0]);
            addPlace(strings[0], strings[1]);
        }
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

        setTag(text, 0, 61);
        setTag(text, 0, 192);
        setTag(text, 0, 685);

        setTag(text, 1, 121);
        setTag(text, 0, 346);
        setTag(text, 1, 220);
        setTag(text, 2, 145);
        setTag(text, 3, 356);
        setTag(text, 4, 143);

        return text.toString();
    }

    private void setTag(Text text, int index, int id) {
        text.setTag(getPlaceTag(data[index][0], data[index][2]), id);
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
