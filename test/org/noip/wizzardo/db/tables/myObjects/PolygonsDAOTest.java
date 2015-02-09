package org.noip.wizzardo.db.tables.myObjects;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.noip.wizzardo.db.DataBase;
import org.noip.wizzardo.grabber.tags.Polygon;

public class PolygonsDAOTest extends TestCase {
    int id;
    private PolygonsDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = new PolygonsDAO(new DataBase().getStatement());
    }

    @After
    public void tearDown() throws Exception {
        dao.delete(id);
    }

    @Test
    public void testCreate() throws Exception {
        //given
        Polygon polygon = new Polygon(1, 2);

        //when
        id = dao.create(polygon);

        //then
        assertEquals(polygon, dao.read(id));
    }

    @Test
    public void testRead() throws Exception {
        //given
        Polygon polygon = new Polygon(3, 2);
        id = dao.create(polygon);

        //when then
        assertEquals(polygon, dao.read(id));
    }

    @Test
    public void testDelete() throws Exception {
        //given
        Polygon polygon = new Polygon(3, 2);
        id = dao.create(polygon);

        //when
        dao.delete(id);

        // then
        assertNull(dao.read(id));
    }
}