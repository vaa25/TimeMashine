package com.my.time_mashine;

import com.my.time_mashine.coords.Yerusalem;

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
        getServletContext().getRequestDispatcher("/map.jsp").forward(req, resp);
    }
}
