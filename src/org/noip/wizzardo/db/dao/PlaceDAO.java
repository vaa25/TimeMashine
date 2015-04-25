package org.noip.wizzardo.db.dao;

import org.noip.wizzardo.objects.Place;
import org.springframework.stereotype.Component;

/**
 * Created by vaa25 on 24.04.2015.
 */
@Component
public interface PlaceDao {
    public int create(Place place);

    public Place read(String title);

    public boolean hasPlace(String title);

    public void delete(String title);
}
