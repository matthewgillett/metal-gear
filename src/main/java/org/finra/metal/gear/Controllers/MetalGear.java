package org.finra.metal.gear.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by k25039 on 8/6/2016.
 */

@RestController
public class MetalGear {

    String json = "{ \"key\" : \"value\" }";
    String jsonNumber = "{ \"key\" : " + (new Random()).nextInt(4) + " }";

    @RequestMapping("/twitterr")
    public String twitter() {
        return json;
    }

    @RequestMapping("/sentiment")
    public String sentiment() {
        return jsonNumber;
    }

    @RequestMapping("/news")
    public String news() {
        return json;
    }

    @RequestMapping("/dayGraph")
    public String dayGraph() {
        return json;
    }

}
