package by.itechart.flowerty.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.solr.model.ContactDocument;
import by.itechart.flowerty.web.service.ContactService;
import by.itechart.flowerty.web.service.UserService;

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
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "contact/list/{page}/{limit}")
    public Page<Contact> page(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
	LOGGER.info("get contact page {} with limit {}", page, limit);

	page = (page == null || page < 1) ? 0 : --page;
	limit = (limit == null || limit <= 0) ? 10 : limit;

	return contactService.getPage(page, limit);
    }

    @ResponseBody
    @RequestMapping(value = "contact/partial-search/{surname}")
    public Page<Contact> searchBySurname(@PathVariable("surname") String surname) {
	LOGGER.info("search by surname: {}", surname);

	Company company = null;
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	UserDetails userPrincipal = null;
	if (!(auth instanceof AnonymousAuthenticationToken)) {
	    userPrincipal = (UserDetails) auth.getPrincipal();
	    if (userPrincipal != null) {
		String login = userPrincipal.getUsername();
		company = userService.getCompanyFor(login);
	    }
	}

	return contactService.findBySurnameStartsWithAndCompany(surname, company.getId()); // get
											   // company
											   // normally
    }

    @ResponseBody
    @RequestMapping(value = "contact/details/{id}")
    public Contact details(@PathVariable("id") Long id) throws Exception {
	LOGGER.info("get details about contact with id: {}", id);

	if (id == null || id <= 0) {
	    throw new Exception("contact id cannot be negative or null");
	}

	Contact contact = null;
	try {
	    contact = contactService.findOne(id);
	} catch (DataIntegrityViolationException e) {
	    LOGGER.error("contact with id {} not found. details:", id, e.getMessage());

	    return null;
	}

	return contact;
    }

    @ResponseBody
    @RequestMapping(value = "contact/search/{page}/{limit}", method = RequestMethod.POST)
    public Page<Contact> search(@RequestBody ContactDocument contact, @PathVariable("page") Integer page,
	    @PathVariable("limit") Integer limit) {
	LOGGER.info("getting search results for contact: {} at page {} with limit {}", contact, page, limit);

	page = (page == null || page < 1) ? 0 : --page;
	limit = (limit == null || limit < 0) ? 10 : limit;

	return contactService.findContacts(contact, page, limit);
    }

    @ResponseBody
    @RequestMapping(value = "contact/remove", method = RequestMethod.POST)
    public void remove(@RequestBody List<Contact> contacts) {
	LOGGER.info("remove contacts. obtained {} contacts", contacts.size());

	contactService.deleteIdIn(fetchIdOfContact(contacts));
    }

    @ResponseBody
    @RequestMapping(value = "contact/save", method = RequestMethod.POST)
    public Contact save(@RequestBody Contact contact) {
	LOGGER.info("save contact: {}", contact.toString());

	contactService.save(contact);

	return contact;
    }

    private static final List<Long> fetchIdOfContact(List<Contact> contacts) {
	List<Long> ids = new ArrayList<Long>(contacts.size());
	for (Contact contact : contacts) {
	    ids.add(contact.getId());
	}

	return ids;
    }
}
