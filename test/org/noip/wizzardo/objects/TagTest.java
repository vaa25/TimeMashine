package org.noip.wizzardo.objects;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class TagTest extends TestCase {
    private Tag tag;

    @Before
    public void setUp() throws Exception {
        tag = new Tag();
    }

    @Test
    public void testInsert() throws Exception {
        //when then
        assertEquals("<tag>string</tag>", tag.insert("string"));
    }

    @Test
    public void testInsertOpen() throws Exception {
        //when then
        assertEquals("<tag>string", tag.insertOpen("string"));
    }

    @Test
    public void testInsertClose() throws Exception {
        //when then
        assertEquals("string</tag>", tag.insertClose("string"));
    }

    @Test
    public void testSetAttribute() throws Exception {
        //when
        tag.setAttribute("first", "1");
        //then
        assertEquals("<tag first=\"1\">string</tag>", tag.insert("string"));
    }

    @Test
    public void testSetAttributes() throws Exception {
        //when
        tag.setAttribute("first", "1");
        tag.setAttribute("second", "2");
        tag.setAttribute("first", "3");
        //then
        assertEquals("<tag second=\"2\" first=\"3\">string</tag>", tag.insert("string"));
    }

    @Test
    public void testSetAttributesWithOverride() throws Exception {
        //when
        tag.setAttribute("first", "1");
        tag.setAttribute("second", "2");
        tag.setAttribute("first", "3");
        //then
        assertEquals("<tag second=\"2\" first=\"3\">string</tag>", tag.insert("string"));
    }
}