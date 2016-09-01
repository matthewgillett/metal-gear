package org.finra.metal.gear.sentiment;

import com.google.gson.Gson;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentFirm {

    private long firmId;
    private String firmName;
    private List<SentimentText> sentimentList;
    private List<SentimentText> sentimentListBatch;
    private SentimentAnalysis analyzer;
    private SentimentData database;

//    public SentimentFirm(SentimentAnalysis analyzer, SentimentData database, String firmName) throws SQLException {
//        this(analyzer, database, database.getFirmId(firmName));
//    }

    public SentimentFirm(SentimentAnalysis analyzer, SentimentData database, long firmId) throws SQLException {
        this.firmId = firmId;
        this.analyzer = analyzer;
        this.database = database;
        sentimentList = database.getSentimentList(firmId);
        sentimentListBatch = new ArrayList<>();

        if (sentimentList == null)
            sentimentList = new ArrayList<>();

        this.firmName = database.getFirmName(firmId);
    }

    public boolean addSentiment(String userId, String text, String createDate) throws SQLException {
        sentimentList.add(new SentimentText(userId, text, analyzer.determineSentiment(text), createDate));
        return database.insertTextData(firmId, sentimentList.get(sentimentList.size() - 1));
    }

    public void addSentimentBatch(String userId, String text, String createDate) {
        sentimentListBatch.add(new SentimentText(userId, text, analyzer.determineSentiment(text), createDate));
    }

    public int[] executeBatch() throws SQLException {
        int[] ret = database.insertTextDataBatch(firmId, sentimentListBatch);
        sentimentList.addAll(sentimentListBatch);
        sentimentListBatch.clear();

        return ret;
    }

    public int updateAverageSentiment() throws SQLException {
        return database.updateAverageSentiment(firmId, getAverageSentiment());
    }

    public int getAverageSentiment() {
        double total = 0;
        for (SentimentText sentiment : sentimentList) {
            total += sentiment.getSentimentDecimal();
        }

        double avg = total / ((double)sentimentList.size());
        long round = Math.round(avg);

        return (int)round;
    }

    public Timestamp getLastUpdate() throws SQLException {
        return database.getLastUpdate(firmId);
    }

    public int refreshTextsSinceDate(String date) throws SQLException {
        return database.refreshTextsSinceDate(firmId, date);
    }

    public String getAverageSentimentJson() throws SQLException {
        Gson gson = new Gson();

        return gson.toJson(new SentimentValue(database.getAverageSentiment(firmId), firmName));
    }

    public String getSentimentTextJson() {
        Gson gson = new Gson();

        List<List<String>> outerList = new ArrayList<>();

        for (SentimentText sentimentText : sentimentList) {
            List<String> innerList = new ArrayList<>();
            innerList.add(sentimentText.getUserId());
            innerList.add(sentimentText.getText());
            innerList.add(String.valueOf(sentimentText.getSentimentInteger()));
            outerList.add(innerList);
        }

        return gson.toJson(outerList);
    }
}
