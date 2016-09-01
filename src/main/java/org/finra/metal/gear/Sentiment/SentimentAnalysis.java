package org.finra.metal.gear.sentiment;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentAnalysis {
    StanfordCoreNLP pipeline;

    public SentimentAnalysis(String properties) {
        pipeline = new StanfordCoreNLP(properties);
    }

    public double determineSentiment(String text) {
        Annotation annotation = new Annotation(text);

        pipeline.annotate(annotation);

        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        int total = 0;
        int count = 0;

        for (CoreMap sentence : sentences) {
            Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
            total += RNNCoreAnnotations.getPredictedClass(tree);
            count++;
        }

        return (double)total / count;
    }
}
