package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Created by Б on 30.01.2015.
 */
//@Component(value = "polygonsDao")
public class PolygonsSpringDao extends JdbcDaoSupport implements PolygonsDao {
    private String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS polygons (" +
            "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
            "x DOUBLE PRECISION NOT NULL," +
            "y DOUBLE PRECISION NOT NULL)";
    private String INSERT = "INSERT INTO polygons VALUES (DEFAULT, ?,?) RETURNING id";
    private String SELECT_BY_ID = "SELECT x,y FROM polygons WHERE id=?";
    private String DELETE = "DELETE FROM polygons WHERE id=?";

    public PolygonsSpringDao() {
        getJdbcTemplate().execute(CREATE_TABLE_IF_NOT_EXISTS);
    }

    public int create(Polygon polygon) {
        SqlRowSet rowSet = getJdbcTemplate().queryForRowSet(INSERT, polygon.getX(), polygon.getY());
        return rowSet.getInt("id");
    }

    public Polygon read(int id) {
        return getJdbcTemplate().queryForObject(SELECT_BY_ID, Polygon.class, id);
    }

    public void delete(int id) {
        getJdbcTemplate().update(DELETE, id);
    }
}
