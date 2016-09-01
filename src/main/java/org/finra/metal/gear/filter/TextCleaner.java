package org.finra.metal.gear.filter;

import com.google.common.base.CharMatcher;

import java.util.List;

public class TextCleaner {
    private CharMatcher matcher = CharMatcher.javaLetterOrDigit().or(CharMatcher.breakingWhitespace());
    String text;

    public TextCleaner(String text) {
        this.text = text;
    }

    public void removeNonAlphanumeric() {
        text = matcher.retainFrom(text);
    }

    public void removeListOfStrings(List<String> removeList) {
        for (String remove : removeList) {
            if (remove != null && !remove.isEmpty())
                text = text.replaceAll(remove, "");
        }
    }

    @Override
    public String toString() {
        return text;
    }
}