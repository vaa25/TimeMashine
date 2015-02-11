package org.noip.wizzardo;

import junit.framework.TestCase;
import org.junit.Test;
import org.noip.wizzardo.objects.Text;

public class Acts1PreparerTest extends TestCase {

    @Test
    public void testGetPlaces() throws Exception {
        System.out.println(new Text(TestUtils.loadText("acts1.txt")).getIndexed());
    }
}