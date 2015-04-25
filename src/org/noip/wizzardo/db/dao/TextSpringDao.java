package org.noip.wizzardo.db.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Created by Б on 30.01.2015.
 */
public class TextSpringDao extends JdbcDaoSupport implements TextDao {
    private String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS texts (" +
            "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
            "string TEXT NOT NULL)";
    private String INSERT = "INSERT INTO texts VALUES (DEFAULT, ?) RETURNING id";
    private String SELECT_BY_ID = "SELECT string from texts WHERE id=?";
    private String DELETE = "DELETE from texts WHERE id=?";

    public TextSpringDao() {
        getJdbcTemplate().execute(CREATE_TABLE_IF_NOT_EXISTS);
    }

    public int create(String text) {
        SqlRowSet rowSet = getJdbcTemplate().queryForRowSet(INSERT, text);
        return rowSet.getInt("id");
    }

    public String read(int id) {
        return getJdbcTemplate().queryForObject(SELECT_BY_ID, String.class, id);
    }

    public void delete(int id) {
        getJdbcTemplate().update(DELETE, id);
    }
}
