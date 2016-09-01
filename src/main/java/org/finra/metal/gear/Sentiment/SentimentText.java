package org.finra.metal.gear.sentiment;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentText {
    private String userId;
    private String text;
    private transient double sentimentDecimal;
    private int sentiment;
    private String createDate;

    public SentimentText(String userId, String text, double sentimentDecimal, String createDate) {
        this.userId = userId;
        this.text = text;
        this.sentimentDecimal = sentimentDecimal;
        this.sentiment = (int)Math.round(sentimentDecimal);
        this.createDate = createDate;
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

    public String getCreateDate() {
        return createDate;
    }
}
