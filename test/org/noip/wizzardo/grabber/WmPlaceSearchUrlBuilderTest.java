package org.noip.wizzardo.grabber;

import junit.framework.TestCase;
import org.junit.Test;
import org.noip.wizzardo.grabber.tags.Polygon;

public class WmPlaceSearchUrlBuilderTest extends TestCase {
    private final int Q = 62;
    private final int LAT = 67;
    private final int LON = 72;
    private final int FORMAT = 80;
    private final int PACK = 90;
    private final int LANGUAGE = 100;
    private final int PAGE = 106;
    private final int COUNT = 114;
    private final int CATEGORY = 126;
    private final int CATEGORIES_OR = 141;
    private final int CATEGORIES_AND = 157;
    private final int DISTANCE = 167;
    private String original = "http://api.wikimapia.org/?key=example&function=place.search&q=&lat=&lon=&format=json&pack=&language=&page=1&count=50&category=&categories_or=&categories_and=&distance=";

    @Test
    public void testSetKey() throws Exception {
        String key = "erfg890234f";
        assertEquals(original.replaceAll("example", key), new WmPlaceSearchUrlBuilder().setKey(key).build());
    }

    @Test
    public void testSetPolygon() throws Exception {
        Polygon polygon = new Polygon(31, 56);
        assertEquals(original.substring(0, LAT)
                .concat(String.valueOf(polygon.getY())
                        .concat(original.substring(LAT, LON))
                        .concat(String.valueOf(polygon.getX()))
                        .concat(original.substring(LON)))
                , new WmPlaceSearchUrlBuilder().setPolygon(polygon).build());
    }

    @Test
    public void testSetLatInt() throws Exception {
        int lat = 67;
        assertEquals(original.substring(0, LAT)
                .concat(String.valueOf(lat)
                        .concat(original.substring(LAT)))
                , new WmPlaceSearchUrlBuilder().setLat(lat).build());
    }

    @Test
    public void testSetLatDouble() throws Exception {
        double lat = 67.3467;
        assertEquals(original.substring(0, LAT)
                .concat(String.valueOf(lat)
                        .concat(original.substring(LAT)))
                , new WmPlaceSearchUrlBuilder().setLat(lat).build());
    }

    @Test
    public void testSetLatString() throws Exception {
        String lat = "67e-3";
        assertEquals(original.substring(0, LAT)
                .concat(lat
                        .concat(original.substring(LAT)))
                , new WmPlaceSearchUrlBuilder().setLat(lat).build());
    }

    @Test
    public void testSetLatStringWrong() throws Exception {
        String lat = "3.df";
        assertEquals(original, new WmPlaceSearchUrlBuilder().setLon(lat).build());
    }

    @Test
    public void testSetLonString() throws Exception {
        String lon = "67.3467";
        assertEquals(original.substring(0, LON)
                .concat(lon
                        .concat(original.substring(LON)))
                , new WmPlaceSearchUrlBuilder().setLon(lon).build());
    }

    @Test
    public void testSetLonStringWrong() throws Exception {
        String lon = "67.t467";
        assertEquals(original, new WmPlaceSearchUrlBuilder().setLon(lon).build());
    }

    @Test
    public void testSetLonDouble() throws Exception {
        double lon = 67.3467;
        assertEquals(original.substring(0, LON)
                .concat(String.valueOf(lon)
                        .concat(original.substring(LON)))
                , new WmPlaceSearchUrlBuilder().setLon(lon).build());
    }

    @Test
    public void testSetLonInt() throws Exception {
        int lon = 67;
        assertEquals(original.substring(0, LON)
                .concat(String.valueOf(lon)
                        .concat(original.substring(LON)))
                , new WmPlaceSearchUrlBuilder().setLon(lon).build());
    }

    @Test
    public void testSetQuery() throws Exception {
        String q = "David's City";
        assertEquals(original.substring(0, Q)
                .concat("David%27s+City")
                .concat(original.substring(Q))
                , new WmPlaceSearchUrlBuilder().setQuery(q).build());
    }

    @Test
    public void testSetQueryRussian() throws Exception {
        String q = "Город Давида";
        assertEquals(original.substring(0, Q)
                .concat("%D0%93%D0%BE%D1%80%D0%BE%D0%B4+%D0%94%D0%B0%D0%B2%D0%B8%D0%B4%D0%B0")
                .concat(original.substring(Q))
                , new WmPlaceSearchUrlBuilder().setQuery(q).build());
    }
}