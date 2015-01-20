package org.noip.wizzardo.grabber.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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

    public static String load(Path path) throws NoSuchFileException {
        StringBuilder result = new StringBuilder();
        try {
            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
                for (String line : lines) {
                    result.append(line);
                }
            } else {
                throw new NoSuchFileException(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
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
}
