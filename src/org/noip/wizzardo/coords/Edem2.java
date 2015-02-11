package org.noip.wizzardo.coords;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;

import java.util.ArrayList;

/**
 * Created by Б on 21.01.2015.
 */
public class Edem2 extends Place {
    public Edem2() {
        super(new ArrayList<Polygon>(), new Polygon(47.563685, 37.948225), "Эдем");
        bound = CoordUtil.getCircle(center, 0.1, 10);
    }
}
