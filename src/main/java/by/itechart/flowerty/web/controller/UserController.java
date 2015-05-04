package by.itechart.flowerty.web.controller;

import by.itechart.flowerty.persistence.model.Role;
import by.itechart.flowerty.persistence.model.User;
import by.itechart.flowerty.web.model.UserEditBundle;
import by.itechart.flowerty.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Eugene Putsykovich(slesh) Mar 24, 2015
 *         <p/>
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

    /*@ResponseBody
    @RequestMapping(value = "user/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getList() {
	List<User> allUsers = (List<User>) userService.findAll();

	LOGGER.info("fetch {} users", allUsers.size());

	return allUsers;
    }                       */

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
    public User add(@RequestBody User user) {
        LOGGER.info("add new user with login: {} and password: {}", user.getLogin(), user.getPassword());

        if (user.getPassword() != null) {
            userService.save(user);
        } else {
            userService.update(user);
        }

        user.setPassword(null);

        return user;
    }

    @ResponseBody
     @RequestMapping(value = {"user/list/{page}&{limit}"})
     public Page<User> getPageLimit(@PathVariable("page") Integer page,
                               @PathVariable("limit") Integer limit) throws Exception {
        LOGGER.info("get page with number {}", page);

        // TODO: *add testing for this method

        page = (page == null || page < 1) ? 0 : --page;

        Page<User> pageUsers = userService.getPage(page, limit);

        LOGGER.info("fetch {} users", pageUsers.getTotalElements());

        return pageUsers;
    }

    @ResponseBody
    @RequestMapping(value = {"tempsearch/user/list/{page}"})
    public Page<User> getPage(@PathVariable("page") Integer page) throws Exception {
        LOGGER.info("get page with number {}", page);

        // TODO: *add testing for this method

        page = (page == null || page < 1) ? 0 : --page;


        Page<User> pageUsers = userService.getPage(page, 10);

        LOGGER.info("fetch {} users", pageUsers.getTotalElements());

        return pageUsers;
    }

    @ResponseBody
    @RequestMapping(value = "user/roles")
    public List<Role> getRoles() throws Exception {
        LOGGER.info("get roles");

        return userService.getRoles();
    }
}
