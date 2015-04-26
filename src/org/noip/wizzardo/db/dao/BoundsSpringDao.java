package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.grabber.tags.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Б on 31.01.2015.
 */
@Component
public class BoundsSpringDao extends JdbcDaoSupport implements BoundsDao {
    private String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS bounds (" +
            "place_id INTEGER NOT NULL ," +
            "polygon_id INTEGER NOT NULL)";
    private String INSERT = "INSERT INTO bounds VALUES (?,?)";
    private String SELECT_BY_PLACEID = "SELECT x,y FROM polygons WHERE id IN (SELECT polygon_id FROM bounds WHERE place_id=?)";
    private String DELETE_POLYGONS = "DELETE FROM polygons WHERE id IN (SELECT polygon_id FROM bounds WHERE place_id=?)";
    private String DELETE_BOUNDS = "DELETE FROM bounds WHERE place_id=?";
    @Autowired
    private PolygonsDao polygonsDao;

    @PostConstruct
    private void init() {
        getJdbcTemplate().execute(CREATE_TABLE_IF_NOT_EXISTS);
    }

    public void create(int idPlace, List<Polygon> bound) {
        for (Polygon polygon : bound) {
            int id = polygonsDao.create(polygon);
            getJdbcTemplate().update(INSERT, idPlace, id);
        }
    }

    public List<Polygon> read(int idPlace) {
        List<Polygon> result = getJdbcTemplate().queryForList(SELECT_BY_PLACEID, Polygon.class, idPlace);
        return result;
    }

    public void delete(int idPlace) {
        getJdbcTemplate().update(DELETE_POLYGONS, idPlace);
        getJdbcTemplate().update(DELETE_BOUNDS, idPlace);
    }
}
