package org.noip.wizzardo.grabber.tags;

/**
 * Created by Б on 20.01.2015.
 */
public class Debug {
    private String message;

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Debug{" +
                "message='" + message + '\'' +
                '}';
    }
}
