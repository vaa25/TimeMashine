package org.noip.wizzardo.coords;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;

import java.util.ArrayList;

/**
 * Created by Б on 21.01.2015.
 */
public class Nazareth extends Place {
    public Nazareth() {
        super(new ArrayList<Polygon>(), new Polygon(35.2947807, 32.701291), "Назарет");
    }
}
