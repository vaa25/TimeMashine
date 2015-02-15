package org.noip.wizzardo.objects;

import org.noip.wizzardo.objects.tags.Tag;

import java.util.Arrays;

/**
 * Created by vaa25 on 10.02.2015.
 */
public class Text {
    private boolean wordIsFirst;
    private String[] words;
    private StringBuilder result;
    private String[] punctuation;

    public Text(String source) {
        result = new StringBuilder();
        words = trim(source.split("[^а-яА-Я]+"));
        punctuation = trim(source.split("[а-яА-Я]+"));
        wordIsFirst = source.startsWith(words[0]);
    }

    private String[] trim(String[] array) {
        if ("".equals(array[0])) {
            String[] result = new String[array.length - 1];
            System.arraycopy(array, 1, result, 0, result.length);
            return result;
        } else {
            return array;
        }
    }

    public void setTag(Tag tag) {
        result.append(tag.insert(""));
    }
    public void setTag(Tag tag, int index) {
        tag.setAttribute("id", String.valueOf(index));
        words[index] = tag.insert(words[index]);
    }

    public void setEmptyTag(Tag tag, int index) {
        tag.setAttribute("id", String.valueOf(index));
        words[index] = tag.insertBefore(words[index]);
    }

    public void setTag(Tag tag, int indexStart, int indexFinish) {
        words[indexStart] = tag.insertOpen(words[indexStart]);
        words[indexFinish] = tag.insertClose(words[indexFinish]);
    }

    public int size() {
        return words.length;
    }
    public String toString() {
        buildText();
        return result.toString();
    }

    public String search(String word) {
        StringBuilder builder = new StringBuilder();
        builder.append('"').append(word).append("\" ");
        int empty = builder.length();
        for (int i = 0; i < words.length; i++) {
            if (word.equals(words[i])) {
                builder.append(i).append(' ');
            }
        }
        if (builder.length() == empty) {
            builder.append("not found");
        }
        return builder.toString();
    }
    public String getIndexed() {
        String[] temp = Arrays.copyOf(words, words.length);
        for (int i = 0; i < words.length; i++) {
            words[i] = " " + i + ")" + words[i];
        }
        buildText();
        words = temp;
        return result.toString();
    }

    private void buildText() {
        for (int i = 0; i < Math.min(words.length, punctuation.length); i++) {
            getNextPairOfElements(i);
        }
        getLastElement();
    }

    private void getLastElement() {
        if (words.length > punctuation.length) {
            result.append(words[words.length - 1]);
        } else if (words.length < punctuation.length) {
            result.append(punctuation[punctuation.length - 1]);
        }
    }

    private void getNextPairOfElements(int i) {
        if (wordIsFirst) {
            result.append(words[i]).append(punctuation[i]);
        } else {
            result.append(punctuation[i]).append(words[i]);
        }
    }
}
