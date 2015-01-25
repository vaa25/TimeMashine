package org.noip.wizzardo;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Б on 07.01.2015.
 */
public class TimeMashineServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        req.setAttribute("places", gson.toJson(new Preparer().getPlaces()));
        getServletContext().getRequestDispatcher("/map.jsp").forward(req, resp);
    }

}
