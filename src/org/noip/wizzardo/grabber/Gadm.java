package org.noip.wizzardo.grabber;

/**
 * Created by Б on 18.01.2015.
 */
public class Gadm {
    private int id;
    private int country;
    private int level;
    private int is_last_level;
    private String name;
    private boolean iso;
    private boolean type;
    private String translation;

    @Override
    public String toString() {
        return "Gadm{" +
                "id=" + id +
                ", \ncountry=" + country +
                ", \nlevel=" + level +
                ", \nis_last_level=" + is_last_level +
                ", \nname='" + name + '\'' +
                ", \niso=" + iso +
                ", \ntype=" + type +
                ", \ntranslation='" + translation + '\'' +
                '}';
    }
}