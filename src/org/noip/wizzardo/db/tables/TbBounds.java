package org.noip.wizzardo.db.tables;

import org.noip.wizzardo.grabber.tags.Polygon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Б on 31.01.2015.
 */
public class TbBounds extends Table {
    public TbBounds(Statement statement) {
        super(statement);
    }

    public void create(int idPlace, List<Polygon> bound) {
        TbPolygons polygons = new TbPolygons(statement);
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

    private List<Polygon> readPolygons(List<Integer> idPolygons) {
        List<Polygon> result = new ArrayList<>();
        TbPolygons polygons = new TbPolygons(statement);
        for (int idPolygon : idPolygons) {
            result.add(polygons.read(idPolygon));
        }
        return result;
    }
}
