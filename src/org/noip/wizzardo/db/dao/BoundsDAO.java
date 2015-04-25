package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.grabber.tags.Polygon;

import java.util.List;

/**
 * Created by Б on 31.01.2015.
 */
public interface BoundsDao {

    public void create(int idPlace, List<Polygon> bound);

    public List<Polygon> read(int idPlace);

    public void delete(int idPlace);
}
