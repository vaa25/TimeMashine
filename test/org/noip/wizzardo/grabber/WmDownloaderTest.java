package org.noip.wizzardo.grabber;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.noip.wizzardo.grabber.tags.Wm;
import org.noip.wizzardo.objects.Place;

public class WmDownloaderTest extends TestCase {
    private WmDownloader downloader;
    private Wm wm;

    @Before
    public void setUp() throws Exception {
        downloader = new WmDownloader("Город Давида");
    }

    @Test
    public void testDownloadWm() throws Exception {
        //when
        wm = downloader.downloadWm();
        //then
        assertNull(getPlace("Город Давида"));
    }

    @Test
    public void testSetLanguage() throws Exception {
        //when
        downloader.setLanguage("ru");
        //then
        wm = downloader.downloadWm();
        assertNotNull(getPlace("Город Давида"));
    }

    private Place getPlace(String title) {
        WmObjectGenerator generator = new WmObjectGenerator(wm);
        generator.setPlaceTitle(title);
        return generator.getPlace();
    }
}