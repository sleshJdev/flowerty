package by.itechart.flowerty.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.itechart.flowerty.domain.Address;
import by.itechart.flowerty.domain.Contact;
import by.itechart.flowerty.domain.Entity;

@Controller
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
	return "home/index";
    }
    
    @RequestMapping(name = "list", method = RequestMethod.GET)
    public @ResponseBody Entity getContactList() {
	Address a = new Address();
	a.setTown("Tows1");
	a.setStreet("Street1");
	a.setHouse("House1");
	a.setFlat("Flat1");
	
	Contact c1 = new Contact();
	c1.setAddress(a);
	c1.setBirthday(new Date());
	c1.setEmail("c1@mail.com");
	c1.setFathername("Fathername1");
	c1.setName("Name1");
	c1.setSurname("Surname1");
	
	Contact c2 = new Contact();
	c2.setAddress(a);
	c2.setBirthday(new Date());
	c2.setEmail("c2@mail.com");
	c2.setFathername("Fathername2");
	c2.setName("Name2");
	c2.setSurname("Surname2");
	
	Contact c3 = new Contact();
	c3.setAddress(a);
	c3.setBirthday(new Date());
	c3.setEmail("c3@mail.com");
	c3.setFathername("Fathername23");
	c3.setName("Name3");
	c3.setSurname("Surname3");
	
	List<Contact> list = new ArrayList<Contact>();
	list.add(c1);
	list.add(c2);
	list.add(c3);
	
	a.setContacts(list);
	
	
	Entity e1 = new Entity("AWDSADAS", "123123");
	
	
	return e1;
    }
}
