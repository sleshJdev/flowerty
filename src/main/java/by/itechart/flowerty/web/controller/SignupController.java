package by.itechart.flowerty.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;

/**
 * @author Eugene Putsykovich(slesh) Mar 26, 2015
 *
 *         Signup handler
 */
@Controller
public class SignupController {
	private Logger LOGGER = LoggerFactory.getLogger(SignupController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup() {
		LOGGER.info("move to signup page");
		return "signup/signup";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @RequestBody User newUser) {
		LOGGER.info("register new user: {name:{}, password:{}}", newUser.getLogin(), newUser.getPassword());
		userRepository.save(newUser);

		return "home/index";
	}
}
