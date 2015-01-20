package org.noip.wizzardo.grabber.tags;

/**
 * Created by Б on 18.01.2015.
 */
public class Polygon {
    private double x, y;

    public Polygon(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
