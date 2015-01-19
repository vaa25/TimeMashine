package org.noip.wizardo.grabber;

import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class Util {
    public static String toString(List list) {
        StringBuilder result = new StringBuilder("List has ").append(list.size()).append(" elements\n");
        for (Object elem : list) {
            result.append('\t').append(elem.toString()).append('\n');
        }
        return result.append("End of list").toString();
    }
}
