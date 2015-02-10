package org.noip.wizzardo.db;

import junit.framework.TestCase;
import org.junit.Test;

public class DataBaseTest extends TestCase {

    @Test
    public void testGetStatement() throws Exception {
        //given
        DataBase dataBase = new DataBase();
        //when then
        assertNotNull(dataBase.getStatement());
    }
}