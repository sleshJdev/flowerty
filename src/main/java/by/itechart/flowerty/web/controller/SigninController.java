package by.itechart.flowerty.web.controller;

import by.itechart.flowerty.web.exception.NotFoundException;
import by.itechart.flowerty.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Signin handler
 */
@Controller
public class SigninController {
	private Logger LOGGER = LoggerFactory.getLogger(SigninController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) throws NotFoundException {
		LOGGER.info("move to login page");

		if (logout != null) {
			LOGGER.info("logout user");
			return "home/index";
		}

		return "home/index";
	}
}
