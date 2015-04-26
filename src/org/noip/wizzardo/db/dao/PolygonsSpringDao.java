package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Б on 30.01.2015.
 */
public class PolygonsSpringDao extends JdbcDaoSupport implements PolygonsDao {
    private String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS polygons (" +
            "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
            "x DOUBLE PRECISION NOT NULL," +
            "y DOUBLE PRECISION NOT NULL)";
    private String INSERT = "INSERT INTO polygons VALUES (DEFAULT, ?,?)";
    private String SELECT_BY_ID = "SELECT x,y FROM polygons WHERE id=?";
    private String DELETE = "DELETE FROM polygons WHERE id=?";

    @PostConstruct
    private void init() {
        getJdbcTemplate().execute(CREATE_TABLE_IF_NOT_EXISTS);
    }

    public int create(final Polygon polygon) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"id"});
                        ps.setDouble(1, polygon.getX());
                        ps.setDouble(2, polygon.getY());
                        return ps;
                    }
                },
                keyHolder);
        return (int) keyHolder.getKey();
    }

    public Polygon read(int id) {
        return getJdbcTemplate().queryForObject(SELECT_BY_ID, Polygon.class, id);
    }

    public void delete(int id) {
        getJdbcTemplate().update(DELETE, id);
    }
}
