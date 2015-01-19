package org.noip.wizzardo.coords;

/**
 * Created by Б on 16.01.2015.
 */
public class Util {
    /**
     * Преобразование координат в градусах/минутах/секундах в координаты десятичных градусов
     *
     * @param value "31°46'4404"
     * @return 31.7789
     */
    public static double toWgc(String value) {
        String[] splitted = value.split("[°'\"]");
        double result = Double.parseDouble(splitted[0]) +
                Double.parseDouble(splitted[1]) / 60;
        double seconds = Double.parseDouble(splitted[2]) / 60 / 60;
        while (seconds >= (1.0 / 60)) {
            seconds /= 10;
        }
        result += seconds;
        return result;
    }

    /**
     * Преобразование координат десятичных градусов в координаты в градусах/минутах/секундах
     *
     * @param value 31.7789
     * @return 31°46'44"
     */
    public static String fromWgc(double value) {
        double degrees = value % 1;
        int integer = (int) value;
        int minutes = (int) (degrees * 60);
        int seconds = (int) ((degrees * 60 - minutes) * 60);
        return Integer.toString(integer) + '°' + minutes + '\'' + seconds + '\"';
    }
}
