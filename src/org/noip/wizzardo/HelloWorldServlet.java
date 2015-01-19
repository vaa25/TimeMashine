package org.noip.wizzardo;

import com.google.gson.Gson;
import org.noip.wizzardo.coords.MountOfOlives;
import org.noip.wizzardo.coords.Point;
import org.noip.wizzardo.coords.Yerusalem;
import org.noip.wizzardo.coords.Zion;
import org.noip.wizzardo.grabber.PolygonGrabber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Б on 07.01.2015.
 */
public class HelloWorldServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Point> polyline = new ArrayList<>();
        polyline.add(new Yerusalem());
        polyline.add(new Zion());
        polyline.add(new MountOfOlives());
        Gson gson = new Gson();
        String gsoned = gson.toJson(polyline);
        req.setAttribute("polyline", gsoned);
        try {
            String title = "Город Давида";
            PolygonGrabber grabber = new PolygonGrabber(title);
            grabber.setLanguage("ru");
            grabber.downloadWm();
            req.setAttribute("yerusalem", grabber.getPlace());
//            System.out.println( grabber.getPlace());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/map.jsp").forward(req, resp);
    }
}
