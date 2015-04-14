package by.itechart.flowerty.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *
 *         Signin handler
 */
@Controller
public class LoginController {
	private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@ResponseBody
	@RequestMapping(value = "/login")
	public UserDetails login(@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) throws Exception {
		LOGGER.info("move to login page");

		if (logout != null) {
			LOGGER.info("logout user");
			return null;
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return (UserDetails) auth.getPrincipal();
		}

		return null;
	}
}
