package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Б on 31.01.2015.
 */
@Component
public class PlaceSpringDao extends JdbcDaoSupport implements PlaceDao {
    private String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS places (" +
            "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
            "title TEXT NOT NULL UNIQUE," +
            "center INTEGER NOT NULL)";
    private String INSERT = "INSERT INTO places VALUES (DEFAULT, ?,?) RETURNING id";
    private String SELECT_BY_TITLE = "SELECT * FROM places WHERE title=?";
    private String DELETE = "DELETE from polygons WHERE id=?";

    public PlaceSpringDao() {
        getJdbcTemplate().execute(CREATE_TABLE_IF_NOT_EXISTS);
    }

    public int create(Place place) {
        if (!hasPlace(place.getTitle())) {
            int id = new PolygonsSpringDao().create(place.getCenter());
            SqlRowSet rowSet = getJdbcTemplate().queryForRowSet(INSERT, place.getTitle(), id);
            return rowSet.getInt("id");
        }
        return -1;
    }

    public Place read(String title) {
        SqlRowSet rowSet = getJdbcTemplate().queryForRowSet(SELECT_BY_TITLE, title);
        List<Polygon> bounds = new BoundsSpringDao().read(rowSet.getInt("id"));
        Polygon center = new PolygonsSpringDao().read(rowSet.getInt("center"));
        Place result = new Place(bounds, center, title);
        return result;
    }

    public boolean hasPlace(String title) {
        try {
            SqlRowSet rowSet = getJdbcTemplate().queryForRowSet(SELECT_BY_TITLE, title);
            rowSet.getInt("id");
            return true;
        } catch (InvalidResultSetAccessException e) {
            return false;
        }
    }

    public void delete(String title) {
        if (!hasPlace(title)) return;
        SqlRowSet rowSet = getJdbcTemplate().queryForRowSet(SELECT_BY_TITLE, title);
        new BoundsSpringDao().delete(rowSet.getInt("id"));
        new PolygonsSpringDao().delete(rowSet.getInt("center"));
    }
}
