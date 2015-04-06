package by.itechart.flowerty.web.controller;

import by.itechart.flowerty.web.exception.NotFoundException;
import by.itechart.flowerty.web.model.SigninForm;
import by.itechart.flowerty.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "authenticate", method = RequestMethod.POST)
//	public String login(@RequestParam("login") String login, @RequestParam("password") String password) {
	public String authenticate(@RequestBody SigninForm user) {
		LOGGER.info("try signin user with login: {} and password: {}", user.getLogin(), user.getPassword());

		boolean isExist = false;
		try{
			isExist = (userService.findUserByLoginAndPassword(user.getLogin(), user.getPassword()) != null);
		}catch(Exception e){
			LOGGER.info("login error: {}", e.getMessage());
			isExist = false;
		}
		
		if (isExist) {
			LOGGER.info("success. redirect to home/index page");
			return "home/index";
		} else {
			LOGGER.info("unsuccess. redirect to back: signin/signin");
			return "signin/signin";
		}
	}
}
