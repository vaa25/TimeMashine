package org.noip.wizzardo.grabber.utils;

import junit.framework.TestCase;
import org.junit.Test;
import org.noip.wizzardo.grabber.tags.Polygon;

import java.util.ArrayList;
import java.util.List;

public class GrabberUtilTest extends TestCase {

    @Test
    public void testToString() throws Exception {
        // given
        List list = getToStringGiven();
        // when then
        assertEquals(getToStringThen(), GrabberUtil.toString(list));

    }

    private List getToStringGiven() {
        List result = new ArrayList();
        result.add(1);
        result.add("two");
        result.add(new Polygon(2, 3));
        return result;
    }

    private String getToStringThen() {
        return "List has 3 elements\n" +
                "\t1\n" +
                "\ttwo\n" +
                "\tPolygon{x=2.0, y=3.0}\n" +
                "End of list";
    }

    @Test
    public void testToStringNull() throws Exception {
        List given = null;
        assertEquals("absent", GrabberUtil.toString(given));
    }

    @Test
    public void testDownload() throws Exception {
        // when
        String data = GrabberUtil.download("http://www.google.com");
        // then
        assertTrue(data.length() > 0);
    }
}