package org.finra.metal.gear.Sentiment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Excluder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentFirm {

    private static long textIdNum = 0;

    private long firmId;
    private Map<Long, SentimentText> sentimentMap;
    private SentimentAnalysis analyzer;
    private SentimentData database;

    public SentimentFirm(SentimentAnalysis analyzer, SentimentData database, long firmId) throws SQLException {
        this.firmId = firmId;
        this.analyzer = analyzer;
        this.database = database;
        sentimentMap = database.getSentimentMap(firmId);

        if (sentimentMap == null)
            sentimentMap = new HashMap<>();
    }

    public void addSentiment(String userId, String text) throws SQLException {
        sentimentMap.put(++textIdNum, new SentimentText(userId, text, analyzer.determineSentiment(text)));
        database.insertTextData(firmId, textIdNum, sentimentMap.get(textIdNum));
    }

    public int getAverageSentiment() {
        double total = 0;
        for (Map.Entry<Long, SentimentText> sentiment : sentimentMap.entrySet()) {
            total += sentiment.getValue().getSentimentDecimal();
        }

        return (int)Math.round(total / sentimentMap.size());
    }

    public String getAverageSentimentJson() {
        Gson gson = new Gson();

        return gson.toJson(new SentimentValue(getAverageSentiment()));
    }

    public String getSentimentTextJson() {
        Gson gson = new Gson();

        List<SentimentText> sentimentList = new ArrayList<>();

        for (Map.Entry<Long, SentimentText> entry : sentimentMap.entrySet()) {
            sentimentList.add(entry.getValue());
        }

        return gson.toJson(sentimentList);

    }
}
