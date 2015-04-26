package org.noip.wizzardo.db.dao;

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
public class TextSpringDao extends JdbcDaoSupport implements TextDao {
    private String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS texts (" +
            "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
            "string TEXT NOT NULL)";
    private String INSERT = "INSERT INTO texts VALUES (DEFAULT, ?)";
    private String SELECT_BY_ID = "SELECT string from texts WHERE id=?";
    private String DELETE = "DELETE from texts WHERE id=?";

    @PostConstruct
    private void init() {
        getJdbcTemplate().execute(CREATE_TABLE_IF_NOT_EXISTS);
    }

    public int create(final String text) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT, new String[]{"id"});
                        ps.setString(1, text);
                        return ps;
                    }
                },
                keyHolder);
        return (int) keyHolder.getKey();
    }

    public String read(int id) {
        return getJdbcTemplate().queryForObject(SELECT_BY_ID, String.class, id);
    }

    public void delete(int id) {
        getJdbcTemplate().update(DELETE, id);
    }
}
