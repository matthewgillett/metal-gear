package org.finra.metal.gear.Sentiment;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentText {
    private String userId;
    private String text;

    public SentimentText(String userId, String text) {
        this.userId = userId;
        this.text = text;
    }
}
