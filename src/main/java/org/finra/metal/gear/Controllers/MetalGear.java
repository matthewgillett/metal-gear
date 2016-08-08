package org.finra.metal.gear.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by k25039 on 8/6/2016.
 */

@RestController
public class MetalGear {

    @RequestMapping("/test")
    public String index() {
        return "templates/connect/twitterConnect.html";
    }

}
