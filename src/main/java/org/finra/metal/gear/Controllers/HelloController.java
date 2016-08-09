package org.finra.metal.gear.Controllers;

import org.finra.metal.gear.Sentiment.SentimentAnalysis;
import org.finra.metal.gear.Sentiment.SentimentData;
import org.finra.metal.gear.Sentiment.SentimentFirm;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by k25039 on 8/8/2016.
 */

@RestController
public class HelloController {

    private static final String AWS_SERVER = "metal-gear1.c9dfyqjobtqf.us-east-1.rds.amazonaws.com:5432";
    private static final int AWS_PORT = 5432;
    private static final String AWS_DBNAME = "metal_gear";
    private static final String SCHEMA = "metalgear";

    private void processTweets(String firmName, SentimentFirm firm) throws SQLException {
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Query query = new Query(firmName);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    firm.addSentiment(tweet.getUser().getScreenName(), tweet.getText());
                }
            }
            while ((query = result.nextQuery()) != null);
        }
        catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        firm.updateAverageSentiment();
    }

    @RequestMapping(value = "/twitter/tweets/{firmId}", method = RequestMethod.GET)
    public String helloTwitter(@PathVariable Long firmId) throws SQLException {
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
//        SentimentData database = new SentimentData("localhost", 5439, "metalgear", null, null);
        SentimentData database = new SentimentData(AWS_SERVER, AWS_PORT, AWS_DBNAME, "metal_gear", "metal_gear");
        database.setSchema(SCHEMA);
        String firmName = database.getFirmName(firmId);
        firmName = "Bank of America";

        SentimentFirm firm = new SentimentFirm(analyzer, database, firmId);
//        processTweets(firmName, firm);

        System.out.println(firm.getSentimentTextJson());
        String sentText = firm.getSentimentTextJson();
    	return sentText;
    }

    @RequestMapping(value = "/twitter/sentiment/{firmId}", method = RequestMethod.GET)
    public String sentimentTwitter(@PathVariable String firmId) throws SQLException {
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
//        SentimentData database = new SentimentData( "localhost", 5439, "metalgear", null, null);
        SentimentData database = new SentimentData(AWS_SERVER, AWS_PORT, AWS_DBNAME, "metal_gear", "metal_gear");
        database.setSchema(SCHEMA);

        SentimentFirm firm = new SentimentFirm(analyzer, database, Integer.parseInt(firmId));

//        firm.addSentiment("Some_user1", "This is a good text string.");
//        firm.addSentiment("Some_user3", "This is a bad text string.");
//        firm.addSentiment("Some_user1", "This is the best string in the whole world; it is fantastic!");

        System.out.println(firm.getAverageSentimentJson());

        String avgSentiment = firm.getAverageSentimentJson();

    	return avgSentiment;
    }

    @RequestMapping(value = "/twitter/news/{firmId}", method = RequestMethod.GET)
    public String newsTwitter(@PathVariable String firmId) throws SQLException {
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
//        SentimentData database = new SentimentData( "localhost", 5439, "metalgear", null, null);
        SentimentData database = new SentimentData(AWS_SERVER, AWS_PORT, AWS_DBNAME, "metal_gear", "metal_gear");
        database.setSchema(SCHEMA);

        SentimentFirm firm = new SentimentFirm(analyzer, database, Integer.parseInt(firmId));

        firm.addSentiment("Some_user1", "This is a good text string.");
        firm.addSentiment("Some_user3", "This is a bad text string.");
        firm.addSentiment("Some_user1", "This is the best string in the whole world; it is fantastic!");

        System.out.println(firm.getAverageSentimentJson());

        String avgSentiment = firm.getAverageSentimentJson();

    	return avgSentiment;
    }

    @RequestMapping(value = "/twitter/daygraph/{firmId}", method = RequestMethod.GET)
    public String dayGraphTwitter(@PathVariable String firmId) throws SQLException {
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
//        SentimentData database = new SentimentData( "localhost", 5439, "metalgear", null, null);
        SentimentData database = new SentimentData(AWS_SERVER, AWS_PORT, AWS_DBNAME, "metal_gear", "metal_gear");
        database.setSchema(SCHEMA);

        SentimentFirm firm = new SentimentFirm(analyzer, database, Integer.parseInt(firmId));

        firm.addSentiment("Some_user1", "This is a good text string.");
        firm.addSentiment("Some_user3", "This is a bad text string.");
        firm.addSentiment("Some_user1", "This is the best string in the whole world; it is fantastic!");

        System.out.println(firm.getAverageSentimentJson());

        String avgSentiment = firm.getAverageSentimentJson();

    	return avgSentiment;
    }

}