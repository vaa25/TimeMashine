package org.noip.wizzardo.db.tables.myObjects;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.noip.wizzardo.coords.Babylon;
import org.noip.wizzardo.db.DataBase;
import org.noip.wizzardo.objects.Place;

public class PlaceDAOTest extends TestCase {
    private PlaceDAO dao;
    private Place place;

    @Before
    public void setUp() throws Exception {
        //given
        dao = new PlaceDAO(new DataBase().getStatement());
        place = new Babylon();
    }

    @After
    public void tearDown() throws Exception {
        dao.delete(place.getTitle());
    }

    @Test
    public void testCreate() throws Exception {
        //when
        dao.create(place);

        //then
        assertEquals(place, dao.read(place.getTitle()));
    }

    @Test
    public void testHasPlaceTrue() throws Exception {
        //when
        dao.create(place);

        //then
        assertTrue(dao.hasPlace(place.getTitle()));
    }

    @Test
    public void testHasPlaceFalse() throws Exception {
        //when
        dao.create(place);

        //then
        assertFalse(dao.hasPlace("Несуществующее место"));
    }

    @Test
    public void testRead() throws Exception {
        //given
        dao.create(place);

        //when then
        assertEquals(place, dao.read(place.getTitle()));
    }

    @Test
    public void testDelete() throws Exception {
        //given
        dao.create(place);
        //when
        dao.delete(place.getTitle());
        //then
        assertNull(dao.read(place.getTitle()));
    }
}