package org.noip.wizzardo.coords;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;

import java.util.ArrayList;

/**
 * Created by Б on 21.01.2015.
 */
public class Kapernaum extends Place {
    public Kapernaum() {
        super(new ArrayList<Polygon>(), new Polygon(35.575361, 32.88056), "Капернаум");
    }
}
