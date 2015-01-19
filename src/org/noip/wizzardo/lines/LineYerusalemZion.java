package org.noip.wizzardo.lines;

import org.noip.wizzardo.coords.Coord;
import org.noip.wizzardo.coords.Yerusalem;
import org.noip.wizzardo.coords.Zion;

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

