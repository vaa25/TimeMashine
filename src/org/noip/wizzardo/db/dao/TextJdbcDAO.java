package org.noip.wizzardo.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Б on 30.01.2015.
 */
public class TextJdbcDAO extends AbstractDAO implements TextDao {

    public TextJdbcDAO(Statement statement) {
        super(statement);
        createTable();
    }

    protected void createTable() {
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS texts (" +
                    "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
                    "string TEXT NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(String text) {
        try {
            statement.executeQuery("INSERT INTO texts " +
                    "VALUES (DEFAULT, '" + text + "')" +
                    " RETURNING id").next();
            return statement.getResultSet().getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SQLEXCEPTION;
    }

    public String read(int id) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT string from texts WHERE id=" + id);
            if (resultSet.next()) {
                return resultSet.getString("string");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int id) {
        try {
            statement.execute("DELETE from texts WHERE id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
