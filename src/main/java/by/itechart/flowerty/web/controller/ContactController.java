/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
package by.itechart.flowerty.web.controller;

import java.net.Authenticator.RequestorType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.web.exception.NotFoundException;
import by.itechart.flowerty.web.service.ContactService;

/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
@Controller
public class ContactController {
    private Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @ResponseBody
    @RequestMapping(value = "contact/list/{page}")
    public Page<Contact> getPage(@PathVariable("page") Integer page) {
	LOGGER.info("get contact page with number {}", page);

	page = (page == null || page < 0) ? 1 : --page;

	return contactService.getPage(page, 1);
    }

    @ResponseBody
    @RequestMapping(value = "contact/details/{id}")
    public Contact getById(@PathVariable("id") Long id) throws NotFoundException {
	LOGGER.info("get details about contact with id: {}", id);

	if (id == null || id < 0) {
	    throw new NotFoundException("contact id cannot be negative or null");
	}

	return contactService.getById(id);
    }

    @ResponseBody
    @RequestMapping(value = "contact/save", method = RequestMethod.POST)
    public Contact save(@RequestBody Contact contact) {
	LOGGER.info("save contact: {} {} {}", contact.getName(), contact.getSurname(), contact.getFathername());

	contactService.save(contact);
	
	return contact;
    }
}
