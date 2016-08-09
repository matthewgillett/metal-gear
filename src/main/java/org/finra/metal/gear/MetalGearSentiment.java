package org.finra.metal.gear;

import org.finra.metal.gear.Sentiment.SentimentAnalysis;
import org.finra.metal.gear.Sentiment.SentimentData;
import org.finra.metal.gear.Sentiment.SentimentFirm;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by k26142 on 8/8/16.
 */
public class MetalGearSentiment {

//    public static void main(String[] args) throws IOException, SQLException {
//
//        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
////        SentimentData database = new SentimentData("metal-gear.c9dfyqjobtqf.us-east-1.rds.amazonaws.com", 5432, "metal_gear", "metal_gear", "metal_gear");
//        SentimentData database = new SentimentData("localhost", 5439, "metalgear", null, null);
//
//        database.initDatabase();
//
//        SentimentFirm firm = new SentimentFirm(analyzer, database, 7726);
//
//        firm.addSentimentBatch("Some_user1", "This is a good text string.");
//        firm.addSentimentBatch("Some_user3", "This is a bad text string.");
//        firm.addSentimentBatch("Some_user1", "This is the best string in the whole world; it is fantastic!");
//
//        firm.executeBatch();
//        firm.updateAverageSentiment();
//
//        System.out.println(firm.getAverageSentimentJson());
//        System.out.println(firm.getSentimentTextJson());
//    }
}
