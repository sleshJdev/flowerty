package by.itechart.flowerty.web.controller;

import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.web.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	@ResponseBody
	public User getById(@PathVariable("id") Long id) throws NotFoundException {
		LOGGER.info("id: {}", id);

		if (id <= 0) {
			throw new NotFoundException("user id cannot be negative or 0");
		}

		User user = userRepository.findOne(id);

		if (user == null) {
			throw new NotFoundException(String.format("user with id %s not found", id));
		}

		return user;
	}

	@RequestMapping(value = "user/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getList() {
		List<User> allUsers = (List<User>) userRepository.findAll();

		LOGGER.info("fetch {} users", allUsers.size());

		return allUsers;
	}

	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	@ResponseBody
	public User add(@Validated @RequestBody User newUser) {
		LOGGER.info("add new user with login: {} and password: {}", newUser.getLogin(), newUser.getPassword());

		return userRepository.save(newUser);
	}
}
