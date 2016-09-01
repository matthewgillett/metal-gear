package org.finra.metal.gear.Controllers;

import com.twitter.Extractor;
import org.finra.metal.gear.filter.TextCleaner;
import org.finra.metal.gear.sentiment.SentimentAnalysis;
import org.finra.metal.gear.sentiment.SentimentData;
import org.finra.metal.gear.sentiment.SentimentFirm;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Created by k25039 on 8/8/2016.
 */

@RestController
public class HelloController {
    private static final String SCHEMA = "metalgear";

    public static void processTweets(String firmName, SentimentFirm firm) throws SQLException {
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            Query query = new Query(firmName);
            Timestamp lastUpdate = firm.getLastUpdate();
            String since;
            if (lastUpdate != null)
                since = lastUpdate.toString().substring(0, 10);
            else
                since = LocalDate.now().minusDays(10).toString();
            query.setSince(since);

            firm.refreshTextsSinceDate(since);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    if (!tweet.isRetweet()) {
                        String text = tweet.getText();

                        TextCleaner cleaner = new TextCleaner(text);

                        Extractor extractor = new Extractor();
//                        cleaner.removeListOfStrings(extractor.extractCashtags(text));
//                        cleaner.removeListOfStrings(extractor.extractHashtags(text));
                        cleaner.removeListOfStrings(extractor.extractMentionedScreennames(text));
                        cleaner.removeListOfStrings(extractor.extractURLs(text));
                        cleaner.removeListOfStrings(Collections.singletonList(extractor.extractReplyScreenname(text)));

                        cleaner.removeNonAlphanumeric();

                        firm.addSentiment(tweet.getUser().getScreenName(), cleaner.toString(), dateFormat.format(tweet.getCreatedAt()));
                    }
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
    @CrossOrigin(origins = "http://localhost:8000")
    public String helloTwitter(@PathVariable Long firmId) throws SQLException {
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
        SentimentData database = new SentimentData("localhost", 5439, "metalgear", null, null);
        database.setSchema(SCHEMA);
        String firmName = database.getFirmName(firmId);

        SentimentFirm firm = new SentimentFirm(analyzer, database, firmId);
        processTweets(firmName, firm);

        System.out.println(firm.getSentimentTextJson());
        String sentText = firm.getSentimentTextJson();
    	return sentText;
    }

    @RequestMapping(value = "/twitter/sentiment/{firmId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8000")
    public String sentimentTwitter(@PathVariable String firmId) throws SQLException {
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
        SentimentData database = new SentimentData( "localhost", 5439, "metalgear", null, null);
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
    @CrossOrigin(origins = "http://localhost:8000")
    public String newsTwitter(@PathVariable String firmId) throws SQLException {
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
        SentimentData database = new SentimentData( "localhost", 5439, "metalgear", null, null);
        database.setSchema(SCHEMA);

        SentimentFirm firm = new SentimentFirm(analyzer, database, Integer.parseInt(firmId));

//        firm.addSentiment("Some_user1", "This is a good text string.");
//        firm.addSentiment("Some_user3", "This is a bad text string.");
//        firm.addSentiment("Some_user1", "This is the best string in the whole world; it is fantastic!");

        System.out.println(firm.getAverageSentimentJson());

        String avgSentiment = firm.getAverageSentimentJson();

    	return avgSentiment;
    }

    @RequestMapping(value = "/twitter/daygraph/{firmId}", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:8000")
    public String dayGraphTwitter(@PathVariable String firmId) throws SQLException {
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
        SentimentData database = new SentimentData( "localhost", 5439, "metalgear", null, null);
        database.setSchema(SCHEMA);

        SentimentFirm firm = new SentimentFirm(analyzer, database, Integer.parseInt(firmId));

//        firm.addSentiment("Some_user1", "This is a good text string.");
//        firm.addSentiment("Some_user3", "This is a bad text string.");
//        firm.addSentiment("Some_user1", "This is the best string in the whole world; it is fantastic!");

        System.out.println(firm.getAverageSentimentJson());

        String avgSentiment = firm.getAverageSentimentJson();

    	return avgSentiment;
    }

}