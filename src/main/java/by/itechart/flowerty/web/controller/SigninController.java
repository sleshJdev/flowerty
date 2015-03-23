package by.itechart.flowerty.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.itechart.flowerty.service.UserService;

@Controller
public class SigninController {
    @RequestMapping(value = "signin")
    public String signin() {
	return "signin/signin";
    }
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "authenticate")
    public void authenticate(){
	
    }
}
