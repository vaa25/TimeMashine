package org.noip.wizardo.grabber;

import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class Comment {
    private int place_id;
    private int num;
    private int lang_id;
    private int user_id;
    private int user_ip;
    private String user_photo;
    private String name;
    private String message;
    private int good;
    private int bad;
    private boolean block;
    private int date;
    private int moder_uid;
    private String moder_name;
    private boolean is_deleted;
    private List<Comment> replies;

    @Override
    public String toString() {
        return "Comment{" +
                "place_id=" + place_id +
                ", \nnum=" + num +
                ", \nlang_id=" + lang_id +
                ", \nuser_id=" + user_id +
                ", \nuser_ip=" + user_ip +
                ", \nuser_photo=" + user_photo +
                ", \nname='" + name + '\'' +
                ", \nmessage='" + message + '\'' +
                ", \ngood=" + good +
                ", \nbad=" + bad +
                ", \nblock=" + block +
                ", \ndate=" + date +
                ", \nmoder_uid=" + moder_uid +
                ", \nmoder_name='" + moder_name + '\'' +
                ", \nis_deleted=" + is_deleted +
                ", \nreplies=" + Util.toString(replies) +
                '}';
    }
}
