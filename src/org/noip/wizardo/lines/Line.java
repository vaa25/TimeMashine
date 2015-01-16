package org.noip.wizardo.lines;

import org.noip.wizardo.coords.Coord;

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
