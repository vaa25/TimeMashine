package org.noip.wizzardo.db.tables.myObjects;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.noip.wizzardo.db.dao.BoundsDao;
import org.noip.wizzardo.db.dao.BoundsSpringDao;
import org.noip.wizzardo.grabber.tags.Polygon;

import java.util.ArrayList;
import java.util.List;

public class BoundsDaoTest extends TestCase {
    private final int id = 987654321;
    private BoundsDao dao;
    private List<Polygon> bound;
    @Before
    public void setUp() throws Exception {
//        dao = new BoundsJdbcDAO(new DataBase().getStatement());
        dao = new BoundsSpringDao();
        bound = new ArrayList<>();
        bound.add(new Polygon(1, 2));
        bound.add(new Polygon(2, 3));
    }

    @After
    public void tearDown() throws Exception {
        dao.delete(id);
    }

    @Test
    public void testCreate() throws Exception {
        //when
        dao.create(id, bound);

        //then
        assertEquals(bound, dao.read(id));
    }

    @Test
    public void testRead() throws Exception {
        //given
        dao.create(id, bound);

        //when then
        assertEquals(bound, dao.read(id));
    }

    @Test
    public void testDelete() throws Exception {
        //given
        dao.create(id, bound);

        //when
        dao.delete(id);

        // then
        assertEquals(0, dao.read(id).size());
    }
}