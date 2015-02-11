package org.noip.wizzardo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaa25 on 10.02.2015.
 */
public class Utils {
    private static List<String> lines;

    public static String loadText(String name) {
        lines = new ArrayList<>();
        getResource(name);
        return getString();
    }

    private static String getString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    private static void getResource(String name) {
        try (BufferedReader reader = getBufferedReader(name)) {
            readLines(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedReader getBufferedReader(String name) throws UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(Utils.class.getResourceAsStream(
                "resources" + File.separatorChar + name), "UTF-8"));
    }

    private static void readLines(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
    }
}
