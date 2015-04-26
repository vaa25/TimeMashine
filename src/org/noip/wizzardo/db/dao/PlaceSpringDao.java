package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.noip.wizzardo.objects.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private String INSERT = "INSERT INTO places VALUES (DEFAULT, ?,?)";
    private String SELECT_BY_TITLE = "SELECT * FROM places WHERE title=?";
    private String DELETE = "DELETE from polygons WHERE id=?";
    @Autowired
    private PolygonsDao polygonsDao;
    @Autowired
    private BoundsDao boundsDao;

    @PostConstruct
    private void init() {
        getJdbcTemplate().execute(CREATE_TABLE_IF_NOT_EXISTS);
    }

    public int create(final Place place) {
        if (!hasPlace(place.getTitle())) {
            final int id = polygonsDao.create(place.getCenter());
            KeyHolder keyHolder = new GeneratedKeyHolder();
            getJdbcTemplate().update(
                    new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"id"});
                            ps.setString(1, place.getTitle());
                            ps.setInt(2, id);
                            return ps;
                        }
                    },
                    keyHolder);
            return (int) keyHolder.getKey();
        }
        return -1;
    }

    public Place read(String title) {
        SqlRowSet rowSet = getJdbcTemplate().queryForRowSet(SELECT_BY_TITLE, title);
        List<Polygon> bounds = boundsDao.read(rowSet.getInt("id"));
        Polygon center = polygonsDao.read(rowSet.getInt("center"));
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
