/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
package by.itechart.flowerty.web.controller;

import java.util.ArrayList;
import java.util.List;

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
import by.itechart.flowerty.web.service.ContactService;
import by.itechart.flowerty.web.service.RepositorySolrContactService;

/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *         handler of actions contact
 */
@Controller
public class ContactController {
    private Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private RepositorySolrContactService solrContactService;

    @ResponseBody
    @RequestMapping(value = "contact/list/{page}")
    public Page<Contact> page(@PathVariable("page") Integer page) {
	LOGGER.info("get contact page with number {}", page);

	page = (page == null || page < 1) ? 0 : --page;

	return contactService.getPage(page, 10);
    }

    @ResponseBody
    @RequestMapping(value = "contact/details/{id}")
    public Contact details(@PathVariable("id") Long id) throws Exception {
	LOGGER.info("get details about contact with id: {}", id);

	if (id == null || id < 0) {
	    throw new Exception("contact id cannot be negative or null");
	}

	Contact contact = contactService.findOne(id);

	return contact;
    }

    @ResponseBody
    @RequestMapping(value = "contact/search", method = RequestMethod.POST)
    public Page<Contact> search(@RequestBody Contact contact) {
	LOGGER.info("search contact");

	return contactService.findContacts(contact, 0, 10);
    }

    @RequestMapping(value = "contact/remove", method = RequestMethod.POST)
    public void remove(@RequestBody List<Contact> contacts) {
	LOGGER.info("remove contacts. obtained {} contacts, wicht not remove", contacts.size());
	
	contactService.deleteIdNotIn(fetchId(contacts));
	for (Contact contact : contacts) {
	    contactService.delete(contact.getId());
	}
    }

    @ResponseBody
    @RequestMapping(value = "contact/save", method = RequestMethod.POST)
    public Contact save(@RequestBody Contact contact) {
	LOGGER.info("save contact: {}", contact.toString());
	contactService.save(contact);
	solrContactService.add(contact);
	return contact;
    }
    
    private static final List<Long> fetchId(List<Contact> contacts){
	List<Long> ids = new ArrayList<Long>(contacts.size());
	for(Contact contact : contacts){
	    ids.add(contact.getId());
	}
	
	return ids;
    }
}
