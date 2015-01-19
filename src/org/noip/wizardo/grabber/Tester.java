package org.noip.wizardo.grabber;

/**
 * Created by Б on 19.01.2015.
 */
public class Tester {
    public static void main(String[] args) {
        try {
            String title = "Город Давида";
            PolygonGrabber grabber = new PolygonGrabber(title);
            grabber.setLanguage("ru");
            grabber.downloadWm();
            System.out.println(grabber.getPlace());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
