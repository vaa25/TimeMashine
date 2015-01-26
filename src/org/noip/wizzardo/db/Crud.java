package org.noip.wizzardo.db;

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

    public Crud() {
        init();
    }

    private void init() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = getConnection();
            statement = connection.createStatement();
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
            dropTables();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        Connection result = null;
        try {
            result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hijack", "hijack", "1234");
            System.out.println("Wizzardo postgresql activated");
        } catch (SQLException e) {
            try {
                result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
                System.out.println("localhost postgresql activated");
            } catch (SQLException e1) {
                throw e;
            }
        }
        return result;
    }

    public boolean hasPlace(String title) {
        try {
            return statement.executeQuery("SELECT * FROM places WHERE title='" + title + "'").next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Place> getAllPlaces() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM places");
        List<Place> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(buildPlace(resultSet));
        }
        return result;
    }

    public void setAllPlaces(List<Place> places) throws SQLException {
        for (Place place : places) {
            setPlace(place);
        }
    }

    public Place getPlace(String title) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM places WHERE title='" + title + "'");
            if (resultSet.next()) {
                return buildPlace(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Place buildPlace(ResultSet resultSet) throws SQLException {
        int idPlace = resultSet.getInt("id");
        int idCenter = resultSet.getInt("center");
        String title = resultSet.getString("title");
        resultSet.close();
        Polygon center = getPolygon(idCenter);
        List<Polygon> bound = getBound(idPlace);
        return new Place(bound, center, title);
    }

    private List<Polygon> getBound(int idPlace) throws SQLException {
        List<Polygon> result = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT polygon_id from bounds WHERE place_id=" + idPlace);
        List<Integer> idPolygons = new ArrayList<>();
        while (resultSet.next()) {
            idPolygons.add(resultSet.getInt("polygon_id"));
        }
        for (int idPolygon : idPolygons) {
            result.add(getPolygon(idPolygon));
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

    public void setPlace(Place place) {
        if (!hasPlace(place.getTitle())) {
            try {
                statement.executeQuery("INSERT INTO places " +
                        "VALUES (DEFAULT, '" + place.getTitle() + "'," + insertPolygon(place.getCenter()) + ")" +
                        " RETURNING id").next();

                insertBound(statement.getResultSet().getInt("id"), place.getBound());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertBound(int idPlace, List<Polygon> bound) throws SQLException {
        for (Polygon polygon : bound) {
            statement.execute("INSERT INTO bounds VALUES (" + idPlace + "," + insertPolygon(polygon) + ")");
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

    public void dropTables() {
        try {
            statement.execute("DROP TABLE places");
            statement.execute("DROP TABLE polygons");
            statement.execute("DROP TABLE bounds");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
