package org.noip.wizzardo.db;

import org.noip.wizzardo.coords.Babylon;
import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Б on 22.01.2015.
 */
public class Crud {
    private Statement statement;

    public static void main(String[] args) {
        Crud crud = new Crud();
        crud.init();
    }

    private void init() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
            statement = connection.createStatement();
            createTables();
            setPlace(new Babylon());
            Place baby = getPlace("Вавилон");
            dropTables();

        } catch (SQLException e) {
            e.printStackTrace();
            dropTables();
        }
    }

    public Place getPlace(String title) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT id, center FROM places WHERE title='" + title + "'");
        if (resultSet.next()) {
            int idPlace = resultSet.getInt("id");
            int idCenter = resultSet.getInt("center");
            Polygon center = getPolygon(idCenter);
            List<Polygon> bound = getBound(idPlace);
            return new Place(bound, center, title);
        }
        return null;
    }

    private List<Polygon> getBound(int idPlace) throws SQLException {
        List<Polygon> result = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT polygon_id from bounds WHERE place_id=" + idPlace);
        while (resultSet.next()) {
            result.add(getPolygon(resultSet.getInt("polygon_id")));
        }
        return result;
    }

    private Polygon getPolygon(int polygon_id) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT x,y from polygons WHERE id=" + polygon_id);
        if (resultSet.next()) {
            return new Polygon(resultSet.getDouble("x"), resultSet.getDouble("y"));
        }
        return null;
    }

    public void setPlace(Place place) throws SQLException {
        statement.executeQuery("INSERT INTO places " +
                "VALUES (DEFAULT, '" + place.getTitle() + "'," + insertPolygon(place.getCenter()) + ")" +
                " RETURNING id").next();
        insertBound(statement.getResultSet().getInt("id"), place.getBound());
    }

    private void insertBound(int idPlace, List<Polygon> bound) throws SQLException {
        for (Polygon polygon : bound) {
            statement.execute("INSERT INTO bounds VALUES (DEFAULT ," + idPlace + "," + insertPolygon(polygon) + ")");
        }
        return;
    }

    private int insertPolygon(Polygon polygon) throws SQLException {
        statement.executeQuery("INSERT INTO polygons " +
                "VALUES (DEFAULT, " + polygon.getX() + "," + polygon.getY() + ")" +
                " RETURNING id").next();
        return statement.getResultSet().getInt("id");
    }

    private void createTables() {
        String queryCreatePlaces = "CREATE TABLE IF NOT EXISTS places (" +
                "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "center INTEGER NOT NULL)";
        String queryCreatePolygons = "CREATE TABLE IF NOT EXISTS polygons (" +
                "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
                "x DOUBLE PRECISION NOT NULL," +
                "y DOUBLE PRECISION NOT NULL)";
        String queryCreateBounds = "CREATE TABLE IF NOT EXISTS bounds (" +
                "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
                "place_id INTEGER NOT NULL ," +
                "polygon_id INTEGER NOT NULL)";

        try {
            statement.execute(queryCreatePolygons);
            statement.execute(queryCreateBounds);
            statement.execute(queryCreatePlaces);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropTables() {
        try {
            statement.execute("DROP TABLE places");
            statement.execute("DROP TABLE polygons");
            statement.execute("DROP TABLE bounds");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
