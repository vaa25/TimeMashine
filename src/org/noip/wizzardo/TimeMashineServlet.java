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
        Acts1Preparer preparer = new Acts1Preparer();
        req.setAttribute("places", gson.toJson(preparer.getPlaces()));
        req.setAttribute("text", gson.toJson(preparer.getText()));
        getServletContext().getRequestDispatcher("/map.jsp").forward(req, resp);
    }

}
