package org.finra.metal.gear.Sentiment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by k26142 on 8/8/16.
 */
public class SentimentText {
    private static long textIdNum;
    private long firmId;
    private Map<Long, String> textMap;

    public SentimentText(long firmId) {
        this.firmId = firmId;
        textMap = new HashMap<>();
    }

    public long addText(String text) {
        textMap.put(++textIdNum, text);
        return textIdNum;
    }
}
