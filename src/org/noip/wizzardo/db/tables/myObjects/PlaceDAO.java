package org.noip.wizzardo.db.tables.myObjects;

import org.noip.wizzardo.db.tables.Table;
import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Б on 31.01.2015.
 */
public class PlaceDAO extends Table {
    private Place place;
    public PlaceDAO(Statement statement) {
        super(statement);
    }

    public void create(Place place) {
        if (!hasPlace(place.getTitle())) {
            try {
                statement.executeQuery("INSERT INTO places " +
                        "VALUES (" +
                        "DEFAULT, " +
                        "'" + place.getTitle() + "'," +
                        createCenter(place) + ")" +
                        " RETURNING id").next();
                createBound(place, statement.getResultSet().getInt("id"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createBound(Place place, int id) {
        new BoundsDAO(statement).create(id, place.getBound());
    }

    private int createCenter(Place place) {
        return new PolygonsDAO(statement).create(place.getCenter());
    }

    public boolean hasPlace(String title) {
        try {
            return statement.executeQuery("SELECT * FROM places WHERE title='" + title + "'").next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Place read(String title) {
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
        Polygon center = new PolygonsDAO(statement).read(idCenter);
        List<Polygon> bound = new BoundsDAO(statement).read(idPlace);
        return new Place(bound, center, title);
    }

    public void delete(String title) {
        if (!hasPlace(title)) return;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM places WHERE title='" + title + "'");
            if (resultSet.next()) {
                int idPlace = resultSet.getInt("id");
                int idCenter = resultSet.getInt("center");
                new BoundsDAO(statement).delete(idPlace);
                new PolygonsDAO(statement).delete(idCenter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
