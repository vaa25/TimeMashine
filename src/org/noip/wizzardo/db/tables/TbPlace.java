package org.noip.wizzardo.db.tables;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Б on 31.01.2015.
 */
public class TbPlace extends Table {
    public TbPlace(Statement statement) {
        super(statement);
    }

    public void create(Place place) {
        if (!hasPlace(place.getTitle())) {
            try {
                statement.executeQuery("INSERT INTO places " +
                        "VALUES (" +
                        "DEFAULT, " +
                        "'" + place.getTitle() + "'," +
                        new TbPolygons(statement).create(place.getCenter()) + ")" +
                        " RETURNING id").next();

                new TbBounds(statement).create(statement.getResultSet().getInt("id"), place.getBound());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        Polygon center = new TbPolygons(statement).read(idCenter);
        List<Polygon> bound = new TbBounds(statement).read(idPlace);
        return new Place(bound, center, title);
    }
}
