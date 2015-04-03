package by.itechart.flowerty.web.controller;

import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Signup handler
 */
@Controller
public class SignupController {
	private Logger LOGGER = LoggerFactory.getLogger(SignupController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup() {
		LOGGER.info("move to signup page");
		return "signup/signup";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @RequestBody User newUser) {
		LOGGER.info("register new user: {name:{}, password:{}}", newUser.getLogin(), newUser.getPassword());
		userService.save(newUser);

		return "home/index";
	}
}
