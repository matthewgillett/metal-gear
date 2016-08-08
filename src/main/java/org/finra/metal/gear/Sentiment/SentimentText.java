package org.finra.metal.gear.Sentiment;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentText {
    private String userId;
    private String text;
    private transient double sentimentDecimal;
    private int sentiment;

    public SentimentText(String userId, String text, Double sentimentDecimal) {
        this.userId = userId;
        this.text = text;
        this.sentimentDecimal = sentimentDecimal;
        this.sentiment = (int)Math.round(sentimentDecimal);
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public double getSentimentDecimal() {
        return sentimentDecimal;
    }

    public int getSentimentInteger() {
        return sentiment;
    }
}
