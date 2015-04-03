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
import by.itechart.flowerty.web.model.UserEditBundle;
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

	@ResponseBody
	@RequestMapping(value = "user/details/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserEditBundle getById(@PathVariable("id") Long id) throws Exception {
		LOGGER.info("id: {}", id);

		if (id < 1) {
			throw new Exception("user id cannot be negative or 0");
		}

		return userService.getUserEditBundleFor(id);
	}

	@ResponseBody
	@RequestMapping(value = "user/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getList() {
		List<User> allUsers = (List<User>) userService.findAll();

		LOGGER.info("fetch {} users", allUsers.size());

		return allUsers;
	}

	@RequestMapping(value = "user/delete/{id}")
	public String delete(@PathVariable("id") Long id) throws Exception {
		LOGGER.info("try delete user with id: {}", id);

		if (id < 1) {
			throw new Exception("user id cannot be negative or 0");
		}

		userService.delete(id);

		return "home/index";
	}

	@ResponseBody
	@RequestMapping(value = "user/save", method = RequestMethod.POST)
	public User add(@Validated @RequestBody User newUser) {
		LOGGER.info("add new user with login: {} and password: {}", newUser.getLogin(), newUser.getPassword());

		userService.save(newUser);

		return newUser;
	}

	@ResponseBody
	@RequestMapping(value = "user/list/{page}")
	public List<User> getPage(@PathVariable("page") Integer page) throws Exception {
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
