package org.noip.wizzardo.coords;

import org.noip.wizzardo.grabber.tags.Polygon;

/**
 * Created by Б on 15.01.2015.
 */
public class Yerusalem extends Place {
    public Yerusalem() {
        super(null, new Polygon(Util.toWgc("31°46'42"), Util.toWgc("35°13'54")), "Yerusalem");
    }
}
