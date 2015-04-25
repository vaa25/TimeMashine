package org.noip.wizzardo.db.tables.myObjects;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.noip.wizzardo.db.dao.PolygonsDao;
import org.noip.wizzardo.grabber.tags.Polygon;
import org.springframework.beans.factory.annotation.Autowired;

public class PolygonsDAOTest extends TestCase {
    private int id;
    @Autowired
    private PolygonsDao dao;
    private Polygon polygon;
    @Before
    public void setUp() throws Exception {
        //given
//        dao = new PolygonsJdbcDAO(new DataBase().getStatement());
//        dao = new PolygonsSpringDao();
        polygon = new Polygon(1, 2);
    }

    @After
    public void tearDown() throws Exception {
        dao.delete(id);
    }

    @Test
    public void testCreate() throws Exception {
        //when
        id = dao.create(polygon);

        //then
        assertEquals(polygon, dao.read(id));
    }

    @Test
    public void testRead() throws Exception {
        //given
        id = dao.create(polygon);

        //when then
        assertEquals(polygon, dao.read(id));
    }

    @Test
    public void testDelete() throws Exception {
        //given
        id = dao.create(polygon);

        //when
        dao.delete(id);

        // then
        assertNull(dao.read(id));
    }
}