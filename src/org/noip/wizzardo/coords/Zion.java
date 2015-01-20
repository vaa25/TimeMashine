package org.noip.wizzardo.coords;

import org.noip.wizzardo.grabber.tags.Polygon;

/**
 * Created by Б on 16.01.2015.
 */
public class Zion extends Place {
    public Zion() {
        super(null, new Polygon(Util.toWgc("31°46'22"), Util.toWgc("35°13'48")), "Zion");
    }
}
