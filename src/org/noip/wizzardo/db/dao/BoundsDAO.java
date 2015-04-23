package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.grabber.tags.Polygon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Б on 31.01.2015.
 */
public class BoundsDAO extends AbstractDAO {
    public BoundsDAO(Statement statement) {
        super(statement);
        createTable();
    }

    protected void createTable() {
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS bounds (" +
                    "place_id INTEGER NOT NULL ," +
                    "polygon_id INTEGER NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(int idPlace, List<Polygon> bound) {
        PolygonsDAO polygons = new PolygonsDAO(statement);
        for (Polygon polygon : bound) {
            try {
                statement.execute("INSERT INTO bounds VALUES (" + idPlace + "," + polygons.create(polygon) + ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Polygon> read(int idPlace) {
        return readPolygons(readPolygonsId(idPlace));
    }
    private List<Integer> readPolygonsId(int idPlace) {
        List<Integer> idPolygons = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT polygon_id from bounds WHERE place_id=" + idPlace);
            while (resultSet.next()) {
                idPolygons.add(resultSet.getInt("polygon_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idPolygons;
    }

    public void delete(int idPlace) {
        deletePolygons(readPolygonsId(idPlace));
        deleteBound(idPlace);
    }

    private void deletePolygons(List<Integer> idPolygons) {
        PolygonsDAO polygons = new PolygonsDAO(statement);
        for (int idPolygon : idPolygons) {
            polygons.delete(idPolygon);
        }
    }

    private void deleteBound(int idPlace) {
        try {
            statement.execute("delete from bounds WHERE place_id=" + idPlace);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Polygon> readPolygons(List<Integer> idPolygons) {
        List<Polygon> result = new ArrayList<>();
        PolygonsDAO polygons = new PolygonsDAO(statement);
        for (int idPolygon : idPolygons) {
            result.add(polygons.read(idPolygon));
        }
        return result;
    }
}
