package by.itechart.flowerty.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Rostislav on 31-Mar-15
 */
@Controller
public class LoginController {
	private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@ResponseBody
	@RequestMapping(value = "/login")
	public UserDetails login() throws Exception {
		LOGGER.info("get logged user");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return (UserDetails) auth.getPrincipal();
		}

		return null;
	}
}
