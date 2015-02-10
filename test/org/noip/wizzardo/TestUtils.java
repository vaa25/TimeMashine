package org.noip.wizzardo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by vaa25 on 10.02.2015.
 */
public class TestUtils {
    public static String loadText(String name) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(new File("testResourses//" + name).toPath(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
