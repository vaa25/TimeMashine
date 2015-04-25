package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.grabber.tags.Polygon;

/**
 * Created by Б on 30.01.2015.
 */
public interface PolygonsDao {

    public int create(Polygon polygon);

    public Polygon read(int id);

    public void delete(int id);
}
