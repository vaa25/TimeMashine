package org.noip.wizzardo.db.dao;

/**
 * Created by Б on 30.01.2015.
 */
public interface TextDao {

    public int create(String text);

    public String read(int id);

    public void delete(int id);
}
