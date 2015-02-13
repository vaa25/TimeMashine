package org.noip.wizzardo;

import org.noip.wizzardo.objects.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaa25 on 10.02.2015.
 */
public class Utils {
    private static List<String> lines;
    private static String name;

    public static String loadText(String name2) {
        name = name2;
        lines = new ArrayList<>();
        getTextResource();
        return getString();
    }

    private static String getString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line).append(' ');
        }
        return stringBuilder.toString();
    }

    private static void getTextResource() {
        try (BufferedReader reader = getBufferedReader()) {
            readLines(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedReader getBufferedReader() throws UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(Utils.class.getResourceAsStream(
                "resources" + File.separatorChar + name), "UTF-8"));
    }

    private static void readLines(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
    }

    public static void main(String[] args) {
        Text text = new Text(loadText("acts1.txt"));
        System.out.println(text.getIndexed());
    }
}
