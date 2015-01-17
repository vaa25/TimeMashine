package org.noip.wizardo;

import com.google.gson.Gson;
import org.noip.wizardo.coords.Yerusalem;
import org.noip.wizardo.coords.Zion;
import org.noip.wizardo.lines.LineYerusalemZion;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Б on 07.01.2015.
 */
public class HelloWorldServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("yerusalem", new Yerusalem());
        req.setAttribute("zion", new Zion());
        Gson gson = new Gson();
        String gsoned = gson.toJson(new LineYerusalemZion());
        req.setAttribute("lineYerZion", gsoned);
        getServletContext().getRequestDispatcher("/map.jsp").forward(req, resp);
    }
}
