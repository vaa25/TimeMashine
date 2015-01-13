package com.my.time_mashine;

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
        String requestUri = new String(req.getRequestURI());
        String context = getServletContext().getContextPath();
        if (!context.equals("")) {
            requestUri = requestUri.substring(context.length());
        }
        if (!requestUri.equals("/")) {
            if (requestUri.endsWith(".html")) {
                requestUri = requestUri.replace(".html", ".jsp");
            } else {
                return;
            }
            getServletContext().getRequestDispatcher(requestUri).forward(req, resp);
            return;
        }
        req.setAttribute("name", "Alexander");
        req.setAttribute("hello", "Hello, world! ");
        getServletContext().getRequestDispatcher("/hello.jsp").forward(req, resp);

    }
}
