package org.noip.wizzardo.grabber.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Б on 18.01.2015.
 */
public class Util {
    public static String toString(List list) {
        StringBuilder result = new StringBuilder("List has ").append(list.size()).append(" elements\n");
        for (Object elem : list) {
            result.append('\t').append(elem.toString()).append('\n');
        }
        return result.append("End of list").toString();
    }

    public static String load(Path path) throws IOException {
        StringBuilder result = new StringBuilder();
        List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
        for (String line : lines) {
            result.append(line);
        }
        return result.toString();
    }

    public static void save(Path path, String value) {
        try {
            Files.write(path, value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String download(String urlToRead) {
        System.out.println(urlToRead);
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
