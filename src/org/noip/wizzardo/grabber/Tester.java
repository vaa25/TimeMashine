package org.noip.wizzardo.grabber;

import org.noip.wizzardo.Acts1Preparer;
import org.noip.wizzardo.objects.Place;

import java.util.List;

/**
 * Created by Б on 21.01.2015.
 */
public class Tester {
    public static void main(String[] args) {
        List<Place> places = new Acts1Preparer().getPlaces();
        for (Place place : places) {
            System.out.println(place.getTitle());
        }
    }
}