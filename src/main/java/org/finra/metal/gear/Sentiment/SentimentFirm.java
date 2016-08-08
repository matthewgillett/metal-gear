package org.finra.metal.gear.Sentiment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentFirm {

    private long firmId;
    private Map<Long, Double> sentimentMap;
    private SentimentText sentimentText;
    private SentimentAnalysis analyzer;

    public SentimentFirm(SentimentAnalysis analyzer, long firmId) {
        this.firmId = firmId;
        this.analyzer = analyzer;
        sentimentText = new SentimentText(firmId);
        sentimentMap = new HashMap<>();
    }

    public void addSentiment(String text) {
        long textId = sentimentText.addText(text);
        sentimentMap.put(textId, analyzer.determineSentiment(text));
    }

    public int getAverageSentiment() {
        double total = 0;
        for (Map.Entry<Long, Double> sentiment : sentimentMap.entrySet()) {
            total += sentiment.getValue();
        }

        return (int) total / sentimentMap.size();
    }
}
