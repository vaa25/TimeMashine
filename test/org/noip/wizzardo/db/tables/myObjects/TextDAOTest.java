package org.noip.wizzardo.db.tables.myObjects;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.noip.wizzardo.TestUtils;
import org.noip.wizzardo.db.DataBase;
import org.noip.wizzardo.db.dao.TextJdbcDAO;

public class TextDAOTest extends TestCase {
    private int id;
    private TextJdbcDAO dao;
    private String text = TestUtils.loadText("text.txt");

    @Before
    public void setUp() throws Exception {
        //given
        dao = new TextJdbcDAO(new DataBase().getStatement());
    }

    @After
    public void tearDown() throws Exception {
        dao.delete(id);
    }

    @Test
    public void testCreate() throws Exception {
        //when
        id = dao.create(text);

        //then
        assertEquals(text, dao.read(id));
    }

    @Test
    public void testRead() throws Exception {
        //given
        id = dao.create(text);

        //when then
        assertEquals(text, dao.read(id));
    }

    @Test
    public void testDelete() throws Exception {
        //given
        id = dao.create(text);

        //when
        dao.delete(id);

        // then
        assertNull(dao.read(id));
    }
}