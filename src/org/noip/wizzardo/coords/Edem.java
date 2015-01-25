package org.noip.wizzardo.coords;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;

import java.util.ArrayList;

/**
 * Created by Б on 21.01.2015.
 */
public class Edem extends Place {
    public Edem() {
        super(new ArrayList<Polygon>(), new Polygon(42.717098, 38.288452), "Эдем");
        bound = Util.getCircle(center, 0.1, 100);
    }
}
