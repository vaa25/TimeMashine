package org.noip.wizzardo.objects.tags;

import com.google.gson.Gson;
import org.noip.wizzardo.grabber.tags.Polygon;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vaa25 on 10.02.2015.
 */
public class Tag {
    private String name = "tag";
    private Attributes attributes = new Attributes();

    public Tag(String name) {
        this.name = name;
    }

    public String insert(String string) {
        return openTag() + string + closeTag();
    }

    public String insertOpen(String string) {
        return openTag() + string;
    }

    public String insertClose(String string) {
        return string + closeTag();
    }
    public void setAttribute(String name, String value) {
        attributes.setAttribute(name, value);
    }

    public void setAttribute(String name, Polygon value) {
        attributes.setAttribute(name, new Gson().toJson(value));
    }
    private String closeTag() {
        return "</" + name + '>';
    }

    private String openTag() {
        return '<' + name + attributes.getAttributes() + '>';
    }

    private class Attributes {
        private Map<String, String> attributes = new HashMap<>();
        private StringBuilder result = new StringBuilder();

        public void setAttribute(String name, String value) {
            attributes.put(name, value);
        }

        public String getAttributes() {
            insertAttributes();
            return result.toString();
        }

        private void insertAttributes() {
            for (String s : attributes.keySet()) {
                insertAttribute(s);
            }
        }

        private void insertAttribute(String name) {
            result.append(' ').append(name).append("=\'").append(attributes.get(name)).append('\'');
        }

    }
}
