package test.by.itecharty.flowerty.service.impl;

import by.itechart.flowerty.configuration.Applicationconfiguration;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.model.User;
import by.itechart.flowerty.service.ContactService;
import by.itechart.flowerty.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 23.03.15
 * Time: 7:51
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class TestContactService {
    private AbstractApplicationContext context;
    @Before
    public void before() {
        context = new AnnotationConfigApplicationContext(Applicationconfiguration.class);
    }

    @Test
    public void testGetAllContacts() {
        ContactService service = (ContactService) context.getBean("contactService");
        List<Contact> list = service.getAllContacts();
        Assert.assertEquals(list.size(), 1);
    }

    @After
    public void after() {
        context.close();
    }
}
