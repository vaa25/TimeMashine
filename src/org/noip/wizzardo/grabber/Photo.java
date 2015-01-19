package org.noip.wizzardo.grabber;

/**
 * Created by Б on 18.01.2015.
 */
public class Photo {
    private int id;
    private int size;
    private int status;
    private int object_id;
    private String user_name;
    private long time;
    private String time_str;
    private int last_user_id;
    private String last_user_name;
    private int user_ip;
    private String big_url;
    private String thumbnail_url;
    private String thumbnailRetina_url;

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", \nsize=" + size +
                ", \nstatus=" + status +
                ", \nobject_id=" + object_id +
                ", \nuser_name='" + user_name + '\'' +
                ", \ntime=" + time +
                ", \ntime_str='" + time_str + '\'' +
                ", \nlast_user_id=" + last_user_id +
                ", \nlast_user_name='" + last_user_name + '\'' +
                ", \nuser_ip=" + user_ip +
                ", \nbig_url='" + big_url + '\'' +
                ", \nthumbnail_url='" + thumbnail_url + '\'' +
                ", \nthumbnailRetina_url='" + thumbnailRetina_url + '\'' +
                '}';
    }
}
