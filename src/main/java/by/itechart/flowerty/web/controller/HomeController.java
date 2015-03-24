package by.itechart.flowerty.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 * 
 *         Redirect to home page
 */
@Controller
public class HomeController {
    private Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = { RequestMethod.GET })
    public String index() {
	LOGGER.info("move to home/index.html page");
	
	return "home/index";
    }
}
