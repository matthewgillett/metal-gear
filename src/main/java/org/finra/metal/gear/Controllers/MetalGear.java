package org.finra.metal.gear.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by k25039 on 8/6/2016.
 */

@RestController
public class MetalGear {

    Random random = new Random(System.currentTimeMillis());

    String json = "{ \"key\" : \"value\" }";

    private String getSentimentScore() {
        return "{ \"key\" : " + random.nextInt(4) + " }";
    }

    @RequestMapping("/twitterr")
    @CrossOrigin(origins = "http://localhost:8000")
    public String twitter() {
        return json;
    }

    @RequestMapping("/sentiment")
    @CrossOrigin(origins = "http://localhost:8000")
    public String sentiment() {
        return getSentimentScore();
    }

    @RequestMapping("/news")
    @CrossOrigin(origins = "http://localhost:8000")
    public String news() {
        return json;
    }

    @RequestMapping("/dayGraph")
    @CrossOrigin(origins = "http://localhost:8000")
    public String dayGraph() {
        return json;
    }

}
