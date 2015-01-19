package org.noip.wizzardo.grabber.tags;

/**
 * Created by Б on 18.01.2015.
 */
public class Language {
    private int id;
    private String name;
    private String object_local_slug;
    private String native_name;
    private String object_url;

    @Override
    public String toString() {
        return id == 0 ? "{absent" : "Language{" +
                "id=" + id +
                ", \nname='" + name + '\'' +
                ", \nobject_local_slug='" + object_local_slug + '\'' +
                ", \nnative_name='" + native_name + '\'' +
                ", \nobject_url='" + object_url + '\'' +
                '}';
    }
}
