package org.noip.wizzardo;

import org.noip.wizzardo.objects.Place;

import java.util.List;

/**
 * Created by vaa25 on 22.04.2015.
 */
public interface Preparer {
    public List<Place> getPlaces();

    public String getText();
}
