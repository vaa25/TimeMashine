package org.noip.wizzardo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Б on 22.01.2015.
 */
public class DataBase {
    private Statement statement;

    public DataBase() {
        init();
    }

    private void init() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = getConnection();
            statement = connection.createStatement();
            new TablePreparer(statement).createTables();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        Connection result = getWizzardoConnection();
        if (result == null) {
            result = getLocalConnection();
        }
        return result;
    }

    private Connection getWizzardoConnection() {
        Connection result = null;
        try {
            result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hijack", "hijack", "1234");
            System.out.println("Wizzardo postgresql activated");
        } catch (SQLException e) {
        }
        return result;
    }

    private Connection getLocalConnection() throws SQLException {
        Connection result = null;
        try {
            result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
            System.out.println("localhost postgresql activated");
        } catch (SQLException e1) {
        }
        return result;
    }

    public Statement getStatement() {
        return statement;
    }
}
