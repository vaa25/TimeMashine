package org.noip.wizzardo.grabber;

/**
 * Created by Б on 18.01.2015.
 */
public class EditInfo {
    private int user_id;
    private String user_name;
    private int date;
    private boolean is_unbindable;
    private boolean deletion_state;
    private boolean is_in_deletion_queue;
    private boolean is_in_undeletion_queue;

    @Override
    public String toString() {
        return "EditInfo{" +
                "user_id=" + user_id +
                ", \nuser_name='" + user_name + '\'' +
                ", \ndate=" + date +
                ", \nis_unbindable=" + is_unbindable +
                ", \ndeletion_state=" + deletion_state +
                ", \nis_in_deletion_queue=" + is_in_deletion_queue +
                ", \nis_in_undeletion_queue=" + is_in_undeletion_queue +
                '}';
    }
}
