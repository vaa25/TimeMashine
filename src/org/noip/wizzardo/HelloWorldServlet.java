package org.noip.wizzardo;

import com.google.gson.Gson;
import org.noip.wizzardo.coords.Place;
import org.noip.wizzardo.grabber.WmDownloader;
import org.noip.wizzardo.grabber.WmObjectBuilder;
import org.noip.wizzardo.grabber.tags.Wm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Б on 07.01.2015.
 */
public class HelloWorldServlet extends HttpServlet {
    private List<Place> places;
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        places = new ArrayList<>();
        addPlace("Город Давида", "ru");
        addPlace("Масличная гора", "ru");
        addPlace("Garden of Gethsemane", "en");
        addPlace("Mount Zion", "en");
        changeTitle("Garden of Gethsemane", "Гефсиманский сад");
        req.setAttribute("places", gson.toJson(places));
        getServletContext().getRequestDispatcher("/map.jsp").forward(req, resp);
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
        try {
            WmDownloader grabber = new WmDownloader(title);
            grabber.setLanguage(language);
            Wm wm = grabber.downloadWm();
            WmObjectBuilder builder = new WmObjectBuilder();
            builder.setPlaceTitle(title);
            return (builder.getPlace(wm));
        } catch (IllegalArgumentException | NoSuchFileException e) {
            e.printStackTrace();
        }
        return null;
    }
}
