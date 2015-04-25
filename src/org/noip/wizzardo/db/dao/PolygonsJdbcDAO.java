package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.grabber.tags.Polygon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Б on 30.01.2015.
 */
public class PolygonsJdbcDAO extends AbstractDAO implements PolygonsDao {

    public PolygonsJdbcDAO(Statement statement) {
        super(statement);
        createTable();
    }

    protected void createTable() {
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS polygons (" +
                    "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
                    "x DOUBLE PRECISION NOT NULL," +
                    "y DOUBLE PRECISION NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Polygon polygon) {
        try {
            statement.executeQuery("INSERT INTO polygons " +
                    "VALUES (DEFAULT, " + polygon.getX() + "," + polygon.getY() + ")" +
                    " RETURNING id").next();
            return statement.getResultSet().getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SQLEXCEPTION;
    }

    public Polygon read(int id) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT x,y from polygons WHERE id=" + id);
            if (resultSet.next()) {
                return new Polygon(resultSet.getDouble("x"), resultSet.getDouble("y"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int id) {
        try {
            statement.execute("DELETE from polygons WHERE id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
