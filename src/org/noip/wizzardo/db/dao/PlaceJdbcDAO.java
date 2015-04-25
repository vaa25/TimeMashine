package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Б on 31.01.2015.
 */
public class PlaceJdbcDAO extends AbstractDAO implements PlaceDao {
    public PlaceJdbcDAO(Statement statement) {
        super(statement);
    }

    protected void createTable() {
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS places (" +
                    "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
                    "title TEXT NOT NULL," +
                    "center INTEGER NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(Place place) {
        if (!hasPlace(place.getTitle())) {
            try {
                statement.executeQuery("INSERT INTO places " +
                        "VALUES (" +
                        "DEFAULT, " +
                        "'" + place.getTitle() + "'," +
                        createCenter(place) + ")" +
                        " RETURNING id").next();
                int id = statement.getResultSet().getInt("id");
                createBound(place, id);
                return id;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return SQLEXCEPTION;
    }

    private void createBound(Place place, int id) {
        new BoundsJdbcDAO(statement).create(id, place.getBound());
    }

    private int createCenter(Place place) {
        return new PolygonsJdbcDAO(statement).create(place.getCenter());
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
        Polygon center = new PolygonsJdbcDAO(statement).read(idCenter);
        List<Polygon> bound = new BoundsJdbcDAO(statement).read(idPlace);
        return new Place(bound, center, title);
    }

    public void delete(String title) {
        if (!hasPlace(title)) return;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM places WHERE title='" + title + "'");
            if (resultSet.next()) {
                int idPlace = resultSet.getInt("id");
                int idCenter = resultSet.getInt("center");
                deleteById(idPlace);
                new BoundsJdbcDAO(statement).delete(idPlace);
                new PolygonsJdbcDAO(statement).delete(idCenter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteById(int id) {
        try {
            statement.execute("DELETE FROM places WHERE id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
