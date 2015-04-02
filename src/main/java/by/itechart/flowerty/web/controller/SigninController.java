package by.itechart.flowerty.web.controller;

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
	
	@RequestMapping(value = "signin", method = RequestMethod.GET)
	public String login(@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		LOGGER.info("move to signin page");

		if (logout != null) {
			LOGGER.info("logout user");
		}

		return "signin/signin";
	}
	
//	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
//	public String signin(
//			@RequestParam("username") String username,
//			@RequestParam("password") String password) {
//		LOGGER.info("try signin user with login: {} and password: {}", username, password);
//
//		boolean isExist = (userService.findUserByLoginAndPassword(username, password) != null);
//
//		if (isExist) {
//			LOGGER.info("success. redirect to home/index page");
//			return "home/index";
//		} else {
//			LOGGER.info("unsuccess. redirect to back: signin/signin");
//			return "signin/signin";
//		}
//	}
}
