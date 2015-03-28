package by.itechart.flowerty.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Signin handler
 */
@Controller
public class SigninController {
	private Logger LOGGER = LoggerFactory.getLogger(SigninController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "sigin", method = RequestMethod.GET)
	public String signin() {
		LOGGER.info("move to sigin page");
		return "signin/signin";
	}

	@RequestMapping(value = "signin", method = RequestMethod.POST)
	public String signin(@Validated @RequestBody User user) {
		LOGGER.info("try signin user with login: {} and password: {}", user.getLogin(), user.getPassword());

		boolean isExist = (userRepository.findUserByLoginAndPassword(user.getLogin(), user.getPassword()) != null);
		if (isExist) {
			LOGGER.info("success. redirect to home/index page");
			return "home/index";
		} else {
			LOGGER.info("unsuccess. redirect to back: signin/signin");
			return "signin/signin";
		}
	}
}
