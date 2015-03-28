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

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.exception.NotFoundException;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Handler for user actions.
 */
@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "user/details/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User getById(@PathVariable("id") Long id) throws NotFoundException {
		LOGGER.info("id: {}", id);

		if (id < 1) {
			throw new NotFoundException("user id cannot be negative or 0");
		}

		User user = userRepository.findOne(id);

		if (user == null) {
			throw new NotFoundException(String.format("user with id %s not found", id));
		}

		return user;
	}

	@RequestMapping(value = "user/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> getList() {
		List<User> allUsers = (List<User>) userRepository.findAll();

		LOGGER.info("fetch {} users", allUsers.size());

		return allUsers;
	}

	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	public @ResponseBody User add(@Validated @RequestBody User newUser) {
		LOGGER.info("add new user with login: {} and password: {}", newUser.getLogin(), newUser.getPassword());

		return userRepository.save(newUser);
	}

	@RequestMapping(value = "user/list/{page}")
	public @ResponseBody List<User> getPage(@PathVariable("page") Integer page) throws NotFoundException {
		LOGGER.info("get page with number {}", page);

		// TODO: change throw exception type. maybe BadRequestException
		// TODO: add testing for this method

		if (page == null || page < 1) {
			page = 1;
		}
		--page;

		List<User> pageUsers = userRepository.findAll(new PageRequest(page, 10)).getContent();

		LOGGER.info("fetch {} users", pageUsers.size());

		return pageUsers;
	}
}
