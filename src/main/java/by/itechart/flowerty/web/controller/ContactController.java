/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
package by.itechart.flowerty.web.controller;

import java.util.HashSet;

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
import by.itechart.flowerty.model.Phone;
import by.itechart.flowerty.model.Phone.PHONE_TYPE;
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
	
	page = (page == null || page < 1) ? 0 : --page;
	
	return contactService.getPage(page, 1);
    }
    
    @ResponseBody
    @RequestMapping(value = "contact/details/{id}")
    public Contact getById(@PathVariable("id") Long id) throws NotFoundException {
	LOGGER.info("get details about contact with id: {}", id);
	
	if (id == null || id < 0) {
	    throw new NotFoundException("contact id cannot be negative or null");
	}

	final Phone phone = new Phone();
	phone.setId(1L);
	phone.setCountry("+375");
	phone.setOperator("029");
	phone.setNumber("8272039");
	phone.setType(PHONE_TYPE.CELL);
	phone.setComment("nice number");
	
	final Phone phone1 = new Phone();
	phone1.setId(2L);
	phone1.setCountry("+375");
	phone1.setOperator("029");
	phone1.setNumber("8272037");
	phone1.setType(PHONE_TYPE.HOME);
	phone1.setComment("cool number");
	
	Contact contact = contactService.getById(id);
	contact.setPhones(new HashSet<Phone>(){{
	    add(phone);
	    add(phone1);
	}});
	
	return contact;
    }

    @ResponseBody
    @RequestMapping(value = "contact/save", method = RequestMethod.POST)
    public Contact save(@RequestBody Contact contact) {
	LOGGER.info("save contact: {} {} {}", contact.getName(), contact.getSurname(), contact.getFathername());
	
	contactService.save(contact);
	
	return contact;
    }
}
