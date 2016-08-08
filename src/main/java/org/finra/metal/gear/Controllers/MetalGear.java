package org.finra.metal.gear.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by k25039 on 8/6/2016.
 */

@RestController
public class MetalGear {

    @RequestMapping("/twitterr")
    public String twitter() {
        return "twitter";
    }

    @RequestMapping("/sentiment")
    public String sentiment() {
        return "sentiment";
    }

    @RequestMapping("/news")
    public String news() {
        return "news";
    }

    @RequestMapping("/dayGraph")
    public String dayGraph() {
        return "dayGraph";
    }

}
