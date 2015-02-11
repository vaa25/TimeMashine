package org.noip.wizzardo.coords;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;

import java.util.ArrayList;

/**
 * Created by Б on 21.01.2015.
 */
public class Babylon extends Place {
    public Babylon() {
        super(new ArrayList<Polygon>(3), null, "Вавилон");
        bound.add(new Polygon(44.416902, 32.543229));
        bound.add(new Polygon(44.444338, 32.540380));
        bound.add(new Polygon(44.426806, 32.522259));
        center = CoordUtil.getCenter(bound);
    }
}
