package org.noip.wizzardo.db.dao;

import java.sql.Statement;

/**
 * Created by Б on 30.01.2015.
 */
public abstract class AbstractDAO {
    protected Statement statement;
    protected int SQLEXCEPTION = -1;

    public AbstractDAO(Statement statement) {
        this.statement = statement;
    }

    protected abstract void createTable();
}
