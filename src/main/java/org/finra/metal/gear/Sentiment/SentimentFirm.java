package org.finra.metal.gear.Sentiment;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentFirm {

    private static long textIdNum = 0;

    private long firmId;
    private List<SentimentText> sentimentList;
    private SentimentAnalysis analyzer;
    private SentimentData database;

    public SentimentFirm(SentimentAnalysis analyzer, SentimentData database, String firmName) throws SQLException {
        this(analyzer, database, database.getFirmId(firmName));
    }

    public SentimentFirm(SentimentAnalysis analyzer, SentimentData database, long firmId) throws SQLException {
        this.firmId = firmId;
        this.analyzer = analyzer;
        this.database = database;
        sentimentList = database.getSentimentList(firmId);

        if (sentimentList == null)
            sentimentList = new ArrayList<>();
    }

    public void addSentiment(String userId, String text) throws SQLException {
        sentimentList.add(new SentimentText(userId, text, analyzer.determineSentiment(text)));
        database.insertTextData(firmId, sentimentList.get(sentimentList.size() - 1));
    }

    public void updateAverageSentiment() throws SQLException {
        database.updateAverageSentiment(firmId, getAverageSentiment());
    }

    public int getAverageSentiment() {
        double total = 0;
        for (SentimentText sentiment : sentimentList) {
            total += sentiment.getSentimentDecimal();
        }

        return (int)Math.round(total / sentimentList.size());
    }

    public Timestamp getLastUpdate() throws SQLException {
        return database.getLastUpdate(firmId);
    }

    public String getAverageSentimentJson() {
        Gson gson = new Gson();

        return gson.toJson(new SentimentValue(getAverageSentiment()));
    }

    public String getSentimentTextJson() {
        Gson gson = new Gson();

        return gson.toJson(sentimentList);
    }
}
