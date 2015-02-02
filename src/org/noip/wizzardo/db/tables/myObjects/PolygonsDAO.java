package org.noip.wizzardo.db.tables.myObjects;

import org.noip.wizzardo.db.tables.Table;
import org.noip.wizzardo.grabber.tags.Polygon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Б on 30.01.2015.
 */
public class PolygonsDAO extends Table {

    public PolygonsDAO(Statement statement) {
        super(statement);
    }

    public int create(Object polygon) {
        try {
            statement.executeQuery("INSERT INTO polygons " +
                    "VALUES (DEFAULT, " + ((Polygon) polygon).getX() + "," + ((Polygon) polygon).getY() + ")" +
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


}
