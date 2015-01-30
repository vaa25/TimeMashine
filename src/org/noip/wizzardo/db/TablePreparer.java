package org.noip.wizzardo.db;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Б on 30.01.2015.
 */
public class TablePreparer {
    private Statement statement;

    public TablePreparer(Statement statement) {
        this.statement = statement;
    }

    public void createTables() {
        String queryCreatePlaces = "CREATE TABLE IF NOT EXISTS places (" +
                "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "center INTEGER NOT NULL)";
        String queryCreatePolygons = "CREATE TABLE IF NOT EXISTS polygons (" +
                "id SERIAL NOT NULL UNIQUE PRIMARY KEY," +
                "x DOUBLE PRECISION NOT NULL," +
                "y DOUBLE PRECISION NOT NULL)";
        String queryCreateBounds = "CREATE TABLE IF NOT EXISTS bounds (" +
                "place_id INTEGER NOT NULL ," +
                "polygon_id INTEGER NOT NULL)";
        try {
            statement.execute(queryCreatePolygons);
            statement.execute(queryCreateBounds);
            statement.execute(queryCreatePlaces);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTables() {
        try {
            statement.execute("DROP TABLE places");
            statement.execute("DROP TABLE polygons");
            statement.execute("DROP TABLE bounds");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
