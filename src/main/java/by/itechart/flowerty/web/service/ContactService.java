package by.itechart.flowerty.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.itechart.flowerty.persistence.model.Address;
import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.persistence.model.Phone;
import by.itechart.flowerty.persistence.repository.ContactRepository;
import by.itechart.flowerty.persistence.repository.PhoneRepository;
import by.itechart.flowerty.security.service.UserDetailsServiceImpl;
import by.itechart.flowerty.solr.model.ContactDocument;
import by.itechart.flowerty.solr.repository.ContactDocumentRepository;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;

/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired(required = true)
    private ContactDocumentRepository contactDocumentRepository;
    
    public Page<Contact> getPage(int page, int size) {
	PageRequest pageRequest = new PageRequest(page, size);

	// contacts, which don't have user
	List<Long> ids = contactRepository.findContactsWithoutUser();
	
	Company companyOfUser = userDetailsService.getCurrentContact().getCompany();
	
	return contactRepository.findByIdIsInAndCompany(ids, companyOfUser, pageRequest);
    }

    // NEED TO FIX SOLR !!!!!!!!! FUCK TOY SOLR!!
//    public Page<Contact> getPage(int page, int size) {
//	PageRequest pageRequest = new PageRequest(page, size);
//
//	// contacts, which don't have user
//	List<ContactDocument> contactDocuments = contactDocumentRepository.findAll(pageRequest).getContent();
//
//	// fetch id of these contacts
//	List<Long> ids = fetchIdsFromContactDocumentsCollection(contactDocuments);
//	
//	Company companyOfUser = userDetailsService.getCurrentContact().getCompany();
//	
//	return contactRepository.findByIdIsInAndCompany(ids, companyOfUser, pageRequest);
//    }

    public Page<Contact> findContacts(ContactDocument contact, int page, int size) {
	Company companyOfUser = userDetailsService.getCurrentContact().getCompany();

	List<Long> ids = contactDocumentRepository.findBySearch(contact);
	if (ids == null) {
	    return contactRepository.findByCompany(companyOfUser, new PageRequest(page, size));
	
	} else if (ids.size() == 0) {
	
	    return new PageImpl<Contact>(new ArrayList<Contact>());
	}
	
	return contactRepository.findByIdIsInAndCompany(ids, companyOfUser, new PageRequest(page, size));
    }
    
    private static final List<Long> fetchIdsFromContactDocumentsCollection(List<ContactDocument> contactDocuments) {
	ArrayList<Long> ids = new ArrayList<Long>();
	for (ContactDocument cd : contactDocuments) {
	    ids.add(Long.valueOf(cd.getId()));
	}

	return ids;
    }

    public Page<Contact> findByHelper(List<ContactDocument> contactDocuments, int page, int size) {
	List<Long> ids = fetchIdsFromContactDocumentsCollection(contactDocuments);
	Company companyOfUser = userDetailsService.getCurrentContact().getCompany();

	return contactRepository.findByIdIsInAndCompany(ids, companyOfUser, new PageRequest(page, size));
    }

    public Page<Contact> findByName(String name, int page, int size) {
	List<ContactDocument> contactDocuments = contactDocumentRepository.findByNameOrSurnameAllIgnoreCase(name, name);

	return findByHelper(contactDocuments, page, size);
    }

    public Page<Contact> findBySurname(String name, int page, int size) {
	List<ContactDocument> contactDocuments = contactDocumentRepository.findByNameContains(name);

	return findByHelper(contactDocuments, page, size);
    }

    public Contact findOne(Long id) {

	return contactRepository.findOne(id);
    }

    private List<Long> processPhonesAndGetId(Contact contact) {
	List<Long> phonesId = new ArrayList<Long>(contact.getPhones().size());
	// to avoid empty collection: case, if we remove all phones;
	phonesId.add(-1L);
	for (Phone phone : contact.getPhones()) {
	    phone.setContact(contact);
	    phonesId.add(phone.getId());
	}

	return phonesId;
    }

    @Transactional
    public Contact save(Contact contact) {
	if (contact.getId() == null) {
	    contact.setCompany(userDetailsService.getCurrentContact().getCompany());
	}

	contactRepository.save(contact);

	if (contact.getPhones() != null) {
	    phoneRepository.save(contact.getPhones());
	    phoneRepository.deleteIdNotIn(contact.getId(), processPhonesAndGetId(contact));
	}

	contactDocumentRepository.save(contact.getContactDocument());

	return contact;
    }

    public Page<Contact> findByCompany(Company company, PageRequest pageRequest) {

	return contactRepository.findByCompany(company, pageRequest);
    }

    @Transactional
    public int deleteIdIn(List<Long> list) {
	List<String> idsAsString = Lists.transform(list, Functions.toStringFunction());
	contactDocumentRepository.deleteIdIsIn(idsAsString);

	return contactRepository.deleteIdIsIn(list);
    }

    public List<Contact> findByBirthDate(String date) {
	List<Long> ids = contactDocumentRepository.findByBirthDate(date);

	Company companyOfUser = userDetailsService.getCurrentContact().getCompany();
	
	return contactRepository.findByIdIsInAndCompany(ids, companyOfUser);
    }

    public Page<Contact> findBySurnameStartsWith(String surname, Company company) {
	if (StringUtils.endsWith(surname, " ")) {
	    List<Long> ids = contactDocumentRepository.findBySurnameStartsWithAndCompany(surname, company.getId());

	    return new PageImpl<Contact>(contactRepository.findByIdIsInAndCompany(ids, company));
	}

	return contactRepository.findBySurnameStartingWithAndCompany(surname, company, new PageRequest(0, 10));
    }

    @Transactional
    public void delete(Long id) {
	contactRepository.delete(id);
	contactDocumentRepository.delete(String.valueOf(id));
    }

    public Page<Contact> findBySurnameStartsWithAndCompany(String surname, Long company) {
	List<Long> ids = null;
	
	Company companyOfUser = userDetailsService.getCurrentContact().getCompany();
	
	if (!StringUtils.endsWith(surname, " ")) {
	    ids = contactDocumentRepository.findBySurnameStartsWithAndCompany(surname, company);
	    
	    return contactRepository.findByIdIsInAndCompany(ids, companyOfUser, new PageRequest(0, 10));
	}
	ids = contactDocumentRepository.findBySurnameStartsWithAndCompany(surname, company);
	
	return contactRepository.findByIdIsInAndCompany(ids, companyOfUser, new PageRequest(0, 10));
    }

    public Contact findByAddress(Address address) {
	Contact contact = new Contact();
	
	contact.setAddress(address);
	
	List<Long> ids = contactDocumentRepository.findBySearch(contact.getContactDocument());
	
	Company companyOfUser = userDetailsService.getCurrentContact().getCompany();
	
	return ids.size() == 0 ? null : contactRepository.findByIdIsInAndCompany(Lists.newArrayList(ids.get(0)), companyOfUser).get(0);
    }
}
