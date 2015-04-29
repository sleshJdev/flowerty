/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
package by.itechart.flowerty.web.controller;

import java.util.List;

import by.itechart.flowerty.solr.model.ContactDocument;
import by.itechart.flowerty.web.service.RepositorySolrContactService;
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

import by.itechart.flowerty.persistence.repository.model.Contact;
//import by.itechart.flowerty.solr.model.ContactDocument;
import by.itechart.flowerty.web.service.ContactService;

//import by.itechart.flowerty.web.service.RepositorySolrContactService;
/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 *	handler of actions contact 	
 */
@Controller
public class ContactController {
    private Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private RepositorySolrContactService solrContactService;

    // @Autowired
    // private UserService userService;
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
    public Page<Contact> search(@RequestBody ContactDocument contact) {
	LOGGER.info("search contact");
    return contactService.findContacts(contact, 0, 10);
	//return contactService.getPage(0, 10);
    }

    @RequestMapping(value = "contact/remove", method = RequestMethod.POST)
    public void remove(@RequestBody List<Contact> contacts) {
	LOGGER.info("remove contacts. obtained {} contacts, wicht not remove", contacts.size());
   // now you can use:
   // contactService.deleteIdNotIn(contacts);
	// for (Contact contact : contacts) {
	// contactService.delete(contact.getId());
	// }

	// for test exception handling
	throw new NullPointerException("exception handling is work!");
    }

    @ResponseBody
    @RequestMapping(value = "contact/save", method = RequestMethod.POST)
    public Contact save(@RequestBody Contact contact) {
	LOGGER.info("save contact: {}", contact.toString());
	contactService.save(contact);
    solrContactService.add(contact);
	return contact;
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<String> errorHandler(final Exception exc) {
    // LOGGER.error(exc.getMessage(), exc);
    // return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    // }
}
