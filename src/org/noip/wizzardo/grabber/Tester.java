package org.noip.wizzardo.grabber;

import org.noip.wizzardo.Preparer;
import org.noip.wizzardo.objects.Place;

import java.util.List;

/**
 * Created by Б on 19.01.2015.
 */
public class Tester {
    public static void main(String[] args) {
        List<Place> places = new Preparer().getPlaces();
        for (Place place : places) {
            System.out.println(place.getTitle());
        }
    }
}
