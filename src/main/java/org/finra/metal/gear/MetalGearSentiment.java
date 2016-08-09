package org.finra.metal.gear;

import org.finra.metal.gear.Infrastructure.AwsClientFactory;
import org.finra.metal.gear.Infrastructure.InfrastructureConfiguration;
import org.finra.metal.gear.Sentiment.SentimentAnalysis;
import org.finra.metal.gear.Sentiment.SentimentData;
import org.finra.metal.gear.Sentiment.SentimentFirm;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by k26142 on 8/8/16.
 */
@SpringBootApplication
@EnableAutoConfiguration
@Import({ InfrastructureConfiguration.class })
public class MetalGearSentiment {

    private static AwsClientFactory awsClientFactory;

    public MetalGearSentiment(AwsClientFactory awsClientFactory) {
        this.awsClientFactory = awsClientFactory;
    }

    public static void main(String[] args) throws IOException, SQLException {

        SentimentAnalysis analyzer = new SentimentAnalysis("nlp.properties");
        SentimentData database = new SentimentData(awsClientFactory, "metal-gear.c9dfyqjobtqf.us-east-1.rds.amazonaws.com", 5432, "metal_gear", "metal_gear", "metal_gear");

        database.initDatabase();

        SentimentFirm firm = new SentimentFirm(analyzer, database, 1);

        firm.addSentiment("Some_user1", "This is a good text string.");
        firm.addSentiment("Some_user3", "This is a bad text string.");
        firm.addSentiment("Some_user1", "This is the best string in the whole world; it is fantastic!");

        System.out.println(firm.getAverageSentimentJson());
        System.out.println(firm.getSentimentTextJson());
    }
}
