package org.finra.metal.gear.Controllers;

import org.finra.metal.gear.Sentiment.SentimentAnalysis;
import org.finra.metal.gear.Sentiment.SentimentData;
import org.finra.metal.gear.Sentiment.SentimentFirm;
import org.springframework.social.connect.ConnectionRepository;
//import org.springframework.social.twitter.api.CursoredList;
//import org.springframework.social.twitter.api.Twitter;
//import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.sql.SQLException;

/**
 * Created by k25039 on 8/8/2016.
 */

@RestController
//@RequestMapping("/twitter/tweets/{firmId}")
public class HelloController {

//    private Twitter twitter;
//    private ConnectionRepository connectionRepository;

//    @Inject
//    public HelloController(Twitter twitter, ConnectionRepository connectionRepository) {
//        this.twitter = twitter;
//        this.connectionRepository = connectionRepository;
//    }

    @RequestMapping(value = "/twitter/tweets/{firmId}", method = RequestMethod.GET)
    public String helloTwitter(@PathVariable String firmId) throws SQLException {
    	
    	
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
        SentimentData database = new SentimentData("localhost", 5439, "metalgear");

        SentimentFirm firm = new SentimentFirm(analyzer, database, Integer.parseInt(firmId));

        firm.addSentiment("Some_user1", "This is a good text string.");
        firm.addSentiment("Some_user3", "This is a bad text string.");
        firm.addSentiment("Some_user1", "This is the best string in the whole world; it is fantastic!");

        System.out.println(firm.getSentimentTextJson());
        
        String sentText = firm.getSentimentTextJson();
    	
    	return sentText;
    	
    }

//        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
//        	
//        	//String pathVar
//        	
//        	System.out.println("in helloTwitter" + firmId);
//            return firmId;
//        }
        
 //       return "redirect:/connect/twitter" ; //+ (new Integer((int)firmId)).toString();

//        //model.addAttribute(twitter.userOperations().getUserProfile());
//        model.addAttribute("twitterProfile", "profile");
//        //CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
//        model.addAttribute("friends", "friend");
//        return "hello";
//        //return "hello";
//    }
    
    
    @RequestMapping(value = "/twitter/sentiment/{firmId}", method = RequestMethod.GET)
    public String sentimentTwitter(@PathVariable String firmId) throws SQLException {
    	
    	
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
        SentimentData database = new SentimentData("localhost", 5439, "metalgear");

        SentimentFirm firm = new SentimentFirm(analyzer, database, Integer.parseInt(firmId));

        firm.addSentiment("Some_user1", "This is a good text string.");
        firm.addSentiment("Some_user3", "This is a bad text string.");
        firm.addSentiment("Some_user1", "This is the best string in the whole world; it is fantastic!");

        System.out.println(firm.getAverageSentimentJson());
        
        String avgSentiment = firm.getAverageSentimentJson();
    	
    	return avgSentiment;
    	
    }
    
    @RequestMapping(value = "/twitter/news/{firmId}", method = RequestMethod.GET)
    public String newsTwitter(@PathVariable String firmId) throws SQLException {
    	
    	
        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
        SentimentData database = new SentimentData("localhost", 5439, "metalgear");

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
        SentimentData database = new SentimentData("localhost", 5439, "metalgear");

        SentimentFirm firm = new SentimentFirm(analyzer, database, Integer.parseInt(firmId));

        firm.addSentiment("Some_user1", "This is a good text string.");
        firm.addSentiment("Some_user3", "This is a bad text string.");
        firm.addSentiment("Some_user1", "This is the best string in the whole world; it is fantastic!");

        System.out.println(firm.getAverageSentimentJson());
        
        String avgSentiment = firm.getAverageSentimentJson();
    	
    	return avgSentiment;
    	
    }
    
    

}
