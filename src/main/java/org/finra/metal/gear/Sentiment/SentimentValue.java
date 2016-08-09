package org.finra.metal.gear.Sentiment;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentValue {
    private int sentiment;
    private String firmName;

    public SentimentValue(int sentiment, String firmName) {
        this.sentiment = sentiment;
        this.firmName = firmName;
    }
}
