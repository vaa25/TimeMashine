package org.noip.wizzardo;

import org.noip.wizzardo.grabber.PolygonGrabber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Б on 07.01.2015.
 */
public class HelloWorldServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("yerusalem", getPlace("Город Давида", "ru"));
        req.setAttribute("olives", getPlace("Масличная гора", "ru"));

        getServletContext().getRequestDispatcher("/map.jsp").forward(req, resp);
    }

    private String getPlace(String title, String language) {
        try {
            PolygonGrabber grabber = new PolygonGrabber(title);
            grabber.setLanguage(language);
            grabber.downloadWm();
            return grabber.getPlace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
