package by.itechart.flowerty.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.itechart.flowerty.model.User;
import by.itechart.flowerty.service.UserService;
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
    private UserService userService;

    @RequestMapping(value = "user/details/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getById(@PathVariable("id") Long id) throws NotFoundException {
	LOGGER.info("id: {}", id);

	if (id <= 0) {
	    throw new NotFoundException("user id cannot be negative or 0");
	}
	
	final String login = "slesh";
	final String password = "gtx260";
	
	User returnedUser = new User();
	returnedUser.setId(id);
	returnedUser.setLogin(login);
	returnedUser.setPassword(password);
	
	return returnedUser;
    }

    @RequestMapping(value = "user/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getList() {
	List<User> users = new ArrayList<User>();
	users.add(createMockUser());
	users.add(createMockUser());
	users.add(createMockUser());
	users.add(createMockUser());
	users.add(createMockUser());

	LOGGER.info("fetch list users");
	return users;
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    @ResponseBody
    public User add(@Validated @RequestBody User newUser) {
	LOGGER.info("add new user: {}", newUser);
    
	return newUser;
    }
    
//    private User createValidUser(){
//	final String login = "slesh";
//	final String password = "gtx260";
//	
//	User validdUser = new User();
//	validdUser.setId(1L);
//	validdUser.setLogin(login);
//	validdUser.setPassword(password);
//	
//	return validdUser;
//    }

    private User createMockUser() {
	User user = new User();
	user.setLogin("mock-login-" + new Date());
	user.setPassword("mock-password-" + new Date());

	return user;
    }
}
