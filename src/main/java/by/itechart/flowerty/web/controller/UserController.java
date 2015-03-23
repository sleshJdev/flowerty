package by.itechart.flowerty.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.itechart.flowerty.model.User;

@Controller
public class UserController {
    @RequestMapping(value = "contact/{id}", produces = "application/json")
    public User getById() {
	User user = new User();
	user.setId(1);
	user.setLogin("CoolUser");
	user.setPassword("CoolPassword");
	
	return user;
    }
}
