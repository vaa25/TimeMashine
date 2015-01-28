package org.noip.wizzardo.coords;

import junit.framework.TestCase;
import org.junit.Test;
import org.noip.wizzardo.grabber.tags.Polygon;

import java.util.ArrayList;
import java.util.List;

public class UtilTest extends TestCase {

    @Test
    public void testGetCircle() throws Exception {
        // given
        Polygon center = new Polygon(100, 100);
        int radius = 10;
        int numberOfSides = 4;
        // when then
        assertEquals(org.noip.wizzardo.grabber.utils.Util.toString(Util.getCircle(center, radius, numberOfSides)),
                org.noip.wizzardo.grabber.utils.Util.toString(getCircle()));
    }

    private List<Polygon> getCircle() {
        List<Polygon> then = new ArrayList<>(4);
        then.add(new Polygon(100, 110));
        then.add(new Polygon(110, 100));
        then.add(new Polygon(100, 90));
        then.add(new Polygon(90, 100));
        return then;
    }

    @Test
    public void testGetCenter() throws Exception {
        // given
        List<Polygon> given = getCircle();
        // when then
        assertEquals(new Polygon(100, 100).toString(), Util.getCenter(given).toString());
    }
}