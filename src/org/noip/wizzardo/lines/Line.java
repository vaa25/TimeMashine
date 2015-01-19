package org.noip.wizzardo.lines;

import org.noip.wizzardo.coords.Coord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Б on 16.01.2015.
 */
public class Line extends ArrayList<Coord> {
    protected List<Coord> coords;
    public List<Coord> getCoords() {
        return coords;
    }
}
