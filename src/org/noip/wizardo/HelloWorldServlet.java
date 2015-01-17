package org.noip.wizardo;

import com.google.gson.Gson;
import org.noip.wizardo.coords.MountOfOlives;
import org.noip.wizardo.coords.Point;
import org.noip.wizardo.coords.Yerusalem;
import org.noip.wizardo.coords.Zion;

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
        getServletContext().getRequestDispatcher("/map.jsp").forward(req, resp);
    }
}
