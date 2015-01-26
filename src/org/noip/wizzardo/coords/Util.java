package org.noip.wizzardo.coords;

import org.noip.wizzardo.grabber.tags.Polygon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Б on 16.01.2015.
 */
public class Util {
    public static List<Polygon> getCircle(Polygon center, double radius, int count) {
        List<Polygon> result = new ArrayList<>(count);
        for (double angle = 0; angle < Math.PI * 2; angle += Math.PI * 2 / count) {
            result.add(new Polygon(center.getX() + Math.sin(angle) * radius, center.getY() + Math.cos(angle) * radius));
        }
        return result;
    }

    public static Polygon getCenter(List<Polygon> polygons) {
        double x = 0;
        double y = 0;
        for (Polygon polygon : polygons) {
            x += polygon.getX();
            y += polygon.getY();
        }
        return new Polygon(x / polygons.size(), y / polygons.size());
    }
}
