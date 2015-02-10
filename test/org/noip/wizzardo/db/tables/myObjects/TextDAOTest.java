package org.noip.wizzardo.db.tables.myObjects;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.noip.wizzardo.db.DataBase;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class TextDAOTest extends TestCase {
    private int id;
    private TextDAO dao;
    private String text = loadText();

    @Before
    public void setUp() throws Exception {
        //given
        dao = new TextDAO(new DataBase().getStatement());
        loadText();
    }

    private String loadText() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(new File("testResourses//text.txt").toPath(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
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