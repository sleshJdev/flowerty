package by.itechart.flowerty.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Authenticate handler
 */
@Controller
public class SigninController {
    private Logger LOGGER = LoggerFactory.getLogger(SigninController.class);

    @RequestMapping(value = "signin")
    public String signin() {
	LOGGER.info("move to home");
	return "signin/signin";
    }

    @RequestMapping(value = "authenticate")
    public void authenticate() {
	LOGGER.info("");
    }
}
