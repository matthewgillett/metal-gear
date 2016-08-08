package org.finra.metal.gear.Controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

/**
 * Created by k25039 on 8/8/2016.
 */

@Configuration
public class AppConfiguration {

    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
        viewControllerRegistry.addViewController("/").setViewName("forward:/index.html");
    }

}
