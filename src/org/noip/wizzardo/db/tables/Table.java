package org.noip.wizzardo.db.tables;

import java.sql.Statement;

/**
 * Created by Б on 30.01.2015.
 */
public abstract class Table {
    protected Statement statement;
    protected int SQLEXCEPTION = -1;

    public Table(Statement statement) {
        this.statement = statement;
    }

    protected abstract void createTable();
}
