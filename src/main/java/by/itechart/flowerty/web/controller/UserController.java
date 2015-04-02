package by.itechart.flowerty.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.service.UserService;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Handler for user actions.
 */
@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "user/details/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getById(@PathVariable("id") Long id) throws Exception {
		LOGGER.info("id: {}", id);

		if (id < 1) {
			throw new Exception("user id cannot be negative or 0");
		}

		User user = userService.findOne(id);

		return user;
	}

	@RequestMapping(value = "user/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> getList() {
		List<User> allUsers = (List<User>) userService.findAll();

		LOGGER.info("fetch {} users", allUsers.size());

		return allUsers;
	}

	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	public @ResponseBody User add(@Validated @RequestBody User newUser) {
		LOGGER.info("add new user with login: {} and password: {}", newUser.getLogin(), newUser.getPassword());

		return userService.save(newUser);
	}

	@RequestMapping(value = "user/list/{page}")
	public @ResponseBody List<User> getPage(@PathVariable("page") Integer page) throws Exception {
		LOGGER.info("get page with number {}", page);

		// TODO: maybe implement throw exception if page has incorrect format???
		// TODO: change throw exception type. maybe BadRequestException
		// TODO: *add testing for this method

		if (page == null || page < 1) {
			page = 1;
		}
		--page;

		List<User> pageUsers = userService.findAll(new PageRequest(page, 10));

		LOGGER.info("fetch {} users", pageUsers.size());

		return pageUsers;
	}
}
