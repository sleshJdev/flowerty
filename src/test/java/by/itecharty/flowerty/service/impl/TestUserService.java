package by.itecharty.flowerty.service.impl;

import by.itecharty.flowerty.config.WebAppConfigurationAware;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import by.itechart.flowerty.configuration.Applicationconfiguration;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.service.UserService;

public class TestUserService extends WebAppConfigurationAware {
    private AbstractApplicationContext context;

    @Before
    public void before() {
	context = new AnnotationConfigApplicationContext(Applicationconfiguration.class);
    }

    @Test
    public void testGetUserById() {
	UserService service = (UserService) context.getBean("userService");
	User user = service.getUser(new Long(1));
	Assert.assertNull("User must be null", user);
    }

    @After
    public void after() {
	context.close();
    }
}
