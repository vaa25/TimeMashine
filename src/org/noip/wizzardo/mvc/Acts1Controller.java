package org.noip.wizzardo.mvc;

import com.google.gson.Gson;
import org.noip.wizzardo.Preparer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.logging.Logger;

@Controller
@RequestMapping("/mvc")
public class Acts1Controller {
    private static Logger log = Logger.getLogger(Acts1Controller.class.getName());
    private Preparer preparer;

    @Autowired
    public Acts1Controller(Preparer acts1Preparer) {
        this.preparer = acts1Preparer;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        log.info("Somebody visited site");
        model.addAttribute("message", "Spring 3 MVC TimeMashine");
        Gson gson = new Gson();
//        Preparer preparer = new Preparer();
        model.addAttribute("places", gson.toJson(preparer.getPlaces()))
                .addAttribute("text", preparer.getText());
        System.out.println(model.get("text"));
//        getServletContext().getRequestDispatcher("/map.jsp").forward(req, resp);
        return "map";
    }
}