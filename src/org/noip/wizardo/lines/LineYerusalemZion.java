package org.noip.wizardo.lines;

import org.noip.wizardo.coords.Coord;
import org.noip.wizardo.coords.Yerusalem;
import org.noip.wizardo.coords.Zion;

import java.util.ArrayList;

/**
 * Created by Б on 16.01.2015.
 */
public class LineYerusalemZion extends ArrayList<Coord> {
    public LineYerusalemZion() {
        add(new Yerusalem());
        add(new Zion());
    }
}

