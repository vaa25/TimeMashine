package org.noip.wizzardo.db.tables.myObjects;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.noip.wizzardo.grabber.tags.Polygon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoundsDAOTest extends TestCase {
    private BoundsDAO dao;
    private Statement statement;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        Class.forName("org.postgresql.Driver");
        connection = getConnection();
        statement = connection.createStatement();
        dao = new BoundsDAO(statement);
    }

    @After
    public void tearDown() throws Exception {
        statement.close();
        connection.close();
    }

    @Test
    public void testCreate() throws Exception {
        List<Polygon> list = new ArrayList<>();
        list.add(new Polygon(1, 2));
        list.add(new Polygon(3, 4));
        list.add(new Polygon(5, 6));
        list.add(new Polygon(7, 8));
        dao.create(987654321, list);

        List<Polygon> result = dao.read(987654321);
        assertEquals(list, result);
    }

    @Test
    public void testRead() throws Exception {

    }

    private Connection getConnection() throws SQLException {
        Connection result = getWizzardoConnection();
        if (result == null) {
            result = getLocalConnection();
        }
        return result;
    }

    private Connection getWizzardoConnection() {
        Connection result = null;
        try {
            result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hijack", "hijack", "1234");
            System.out.println("Wizzardo postgresql activated");
        } catch (SQLException e) {
        }
        return result;
    }

    private Connection getLocalConnection() throws SQLException {
        Connection result = null;
        try {
            result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
            System.out.println("localhost postgresql activated");
        } catch (SQLException e1) {
        }
        return result;
    }
}