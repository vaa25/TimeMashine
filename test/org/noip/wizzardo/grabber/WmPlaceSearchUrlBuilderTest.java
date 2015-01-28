package org.noip.wizzardo.grabber;

import junit.framework.TestCase;
import org.junit.Test;
import org.noip.wizzardo.grabber.tags.Polygon;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WmPlaceSearchUrlBuilderTest extends TestCase {
    private final int POSITION_OF_Q = 62;
    private final int POSITION_OF_LAT = 67;
    private final int POSITION_OF_LON = 72;
    private final int POSITION_OF_FORMAT = 80;
    private final int POSITION_OF_PACK = 90;
    private final int POSITION_OF_LANGUAGE = 100;
    private final int POSITION_OF_PAGE = 106;
    private final int POSITION_OF_COUNT = 114;
    private final int POSITION_OF_CATEGORY = 126;
    private final int POSITION_OF_CATEGORIES_OR = 141;
    private final int POSITION_OF_CATEGORIES_AND = 157;
    private final int POSITION_OF_DISTANCE = 167;
    private String original = "http://api.wikimapia.org/?key=example&function=place.search&q=&lat=&lon=&format=json&" +
            "pack=&language=&page=1&count=50&category=&categories_or=&categories_and=&distance=";

    @Test
    public void testSetKey() throws Exception {
        // given
        String key = "erfg890234f";
        // when then
        assertEquals(getSetKeyThen(key), new WmPlaceSearchUrlBuilder().setKey(key).build());
    }

    private String getSetKeyThen(String key) {
        return original.replaceAll("example", key);
    }

    @Test
    public void testSetPolygon() throws Exception {
        // given
        Polygon polygon = new Polygon(31, 56);
        // when then
        assertEquals(getSetPolygonThen(polygon), new WmPlaceSearchUrlBuilder().setPolygon(polygon).build());
    }

    private String getSetPolygonThen(Polygon polygon) {
        return insertInOriginal(POSITION_OF_LAT, polygon.getY(), POSITION_OF_LON, polygon.getX());
    }

    private String insertInOriginal(int index1, double value1, int index2, double value2) {
        return original.substring(0, index1) + value1 + original.substring(index1, index2) + value2 + original.substring(index2);
    }
    @Test
    public void testSetLatInt() throws Exception {
        // given
        int lat = 67;
        // when then
        assertEquals(getSetLatIntThen(lat), new WmPlaceSearchUrlBuilder().setLat(lat).build());
    }

    private String getSetLatIntThen(int given) {
        return insertInOriginal(POSITION_OF_LAT, given);
    }

    private String insertInOriginal(int index, int value) {
        return insertInOriginal(index, String.valueOf(value));
    }

    private String insertInOriginal(int index, String value) {
        return original.substring(0, index) + value + original.substring(index);
    }

    @Test
    public void testSetLatDouble() throws Exception {
        // given
        double lat = 67.3467;
        // when then
        assertEquals(getSetLatDoubleThen(lat), new WmPlaceSearchUrlBuilder().setLat(lat).build());
    }

    private String getSetLatDoubleThen(double given) {
        return insertInOriginal(POSITION_OF_LAT, given);
    }

    private String insertInOriginal(int index, double value) {
        return insertInOriginal(index, String.valueOf(value));
    }
    @Test
    public void testSetLatString() throws Exception {
        // given
        String lat = "67e-3";
        // when then
        assertEquals(getSetLatStringThen(lat), new WmPlaceSearchUrlBuilder().setLat(lat).build());
    }

    private String getSetLatStringThen(String given) {
        return insertInOriginal(POSITION_OF_LAT, given);
    }

    @Test
    public void testSetLatStringWrong() throws Exception {
        // given
        String lat = "3.df";
        // when then
        assertEquals(original, new WmPlaceSearchUrlBuilder().setLon(lat).build());
    }

    @Test
    public void testSetLonString() throws Exception {
        // given
        String lon = "67.3467";
        // when then
        assertEquals(getSetLonString(lon), new WmPlaceSearchUrlBuilder().setLon(lon).build());
    }

    private String getSetLonString(String given) {
        return insertInOriginal(POSITION_OF_LON, String.valueOf(given));
    }

    @Test
    public void testSetLonStringWrong() throws Exception {
        // given
        String lon = "67.t467";
        // when then
        assertEquals(original, new WmPlaceSearchUrlBuilder().setLon(lon).build());
    }

    @Test
    public void testSetLonDouble() throws Exception {
        // given
        double lon = 67.3467;
        // when then
        assertEquals(getSetLonDoubleThen(lon), new WmPlaceSearchUrlBuilder().setLon(lon).build());
    }

    private String getSetLonDoubleThen(double given) {
        return insertInOriginal(POSITION_OF_LON, String.valueOf(given));
    }
    @Test
    public void testSetLonInt() throws Exception {
        // given
        int lon = 67;
        // when then
        assertEquals(getSetLonIntThen(lon), new WmPlaceSearchUrlBuilder().setLon(lon).build());
    }

    private String getSetLonIntThen(int given) {
        return insertInOriginal(POSITION_OF_LON, String.valueOf(given));
    }
    @Test
    public void testSetQuery() throws Exception {
        // given
        String q = "David's City";
        // when then
        assertEquals(getSetQueryThen(q), new WmPlaceSearchUrlBuilder().setQuery(q).build());
    }

    private String getSetQueryThen(String given) {
        try {
            return insertInOriginal(POSITION_OF_Q, URLEncoder.encode(given, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return original;
    }
    @Test
    public void testSetQueryRussian() throws Exception {
        // given
        String q = "Город Давида";
        // when then
        assertEquals(getSetQueryThen(q), new WmPlaceSearchUrlBuilder().setQuery(q).build());
    }

}