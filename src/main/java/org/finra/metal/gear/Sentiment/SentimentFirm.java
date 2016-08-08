package org.finra.metal.gear.Sentiment;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentFirm {

    private static long textIdNum = 0;

    private long firmId;
    private Map<Long, Double> sentimentMap;
    private Map<Long, SentimentText> textMap;
    private SentimentAnalysis analyzer;

    public SentimentFirm(SentimentAnalysis analyzer, long firmId) {
        this.firmId = firmId;
        this.analyzer = analyzer;
        sentimentMap = new HashMap<>();
        textMap = new HashMap<>();
    }

    public void addSentiment(String userId, String text) {

        textMap.put(++textIdNum, new SentimentText(userId, text));
        sentimentMap.put(textIdNum, analyzer.determineSentiment(text));
    }

    public int getAverageSentiment() {
        double total = 0;
        for (Map.Entry<Long, Double> sentiment : sentimentMap.entrySet()) {
            total += sentiment.getValue();
        }

        return (int)Math.round(total / sentimentMap.size());
    }

    public String getAverageSentimentJson() {
        Gson gson = new Gson();

        return gson.toJson(new SentimentValue(getAverageSentiment()));
    }

    public String getSentimentTextJson() {
        Gson gson = new Gson();

        return gson.toJson(textMap);
    }
}
