package org.noip.wizzardo.objects;

/**
 * Created by vaa25 on 10.02.2015.
 */
public class Text {
    boolean wordIsFirst;
    private String[] words;
    private String source;
    private StringBuilder result;
    private String[] punctuation;

    public Text(String source) {
        this.source = source;
        result = new StringBuilder();
        words = source.split("[^а-яА-Я]+");
        punctuation = source.split("[а-яА-Я]+");
        wordIsFirst = source.startsWith(words[0]);
    }

    public void setTag(Tag tag, int index) {
        words[index] = tag.insert(words[index]);
    }

    public String getResult() {
        return result.toString();
    }

    private void insertPunctuation() {
        for (int i = 0; i < Math.min(words.length, punctuation.length); i++) {
            if (wordIsFirst) {
                result.append(words[i]).append(punctuation[i]);
            } else {
                result.append(punctuation[i]).append(words[i]);
            }
        }
        if (words.length > punctuation.length) {
            result.append(words[words.length - 1]);
        } else if (words.length < punctuation.length) {
            result.append(punctuation[punctuation.length - 1]);
        }
    }
}
