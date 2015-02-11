package org.noip.wizzardo.objects;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.noip.wizzardo.objects.tags.Tag;

public class TextTest extends TestCase {
    private Text text;
    private Tag tag;
    private String source = "- И сказал Бог: да будет свет. И стал свет.\n";

    @Before
    public void setUp() throws Exception {
        //given
        text = new Text(source);
        tag = new Tag("tag");
        tag.setAttribute("first", "1");
    }

    @Test
    public void testSetTag() throws Exception {
        //when
        text.setTag(tag, 2);

        //then
        assertEquals("- И сказал <tag first=\"1\">Бог</tag>: да будет свет. И стал свет.\n", text.toString());
    }

    @Test
    public void testSetTag1() throws Exception {
        //when
        text.setTag(tag, 3, 5);

        //then
        assertEquals("- И сказал Бог: <tag first=\"1\">да будет свет</tag>. И стал свет.\n", text.toString());
    }

    @Test
    public void testGetResult() throws Exception {
        //when then
        assertEquals(source, text.toString());
    }
}