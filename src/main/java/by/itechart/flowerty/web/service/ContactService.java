package by.itechart.flowerty.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.itechart.flowerty.dao.repository.ContactRepository;
import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.solr.model.ContactDocument;
import by.itechart.flowerty.solr.repository.ContactDocumentRepository;

/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired (required = true)
    private ContactDocumentRepository contactDocumentRepository;

    public Page<Contact> getPage(int page, int size) {
	return contactRepository.findAll(new PageRequest(page, size));
    }

    public Page<Contact> findContacts(Contact contact, int page, int size) {
        List<Long> ids = contactDocumentRepository.findBySearch(contact.getContactDocument());
        if (ids == null) {
            return contactRepository.findAll(new PageRequest(page, size)); //replace by findByCompany when we know company
        }
        return contactRepository.findByIdIsIn(ids, new PageRequest(page, size));
    }

    public Page<Contact> findByName(String name, int page, int size) {
        List<ContactDocument> contactDocuments = contactDocumentRepository.findByNameOrSurnameAllIgnoreCase(name, name);
        ArrayList<Long> ids = new ArrayList<Long>();
        for (ContactDocument cd: contactDocuments) {
            ids.add(Long.valueOf(cd.getId()));
        }
        return contactRepository.findByIdIsIn(ids, new PageRequest(page, size));
    }
    public Page<Contact> findBySurname(String name, int page, int size) {
        List<ContactDocument> contactDocuments = contactDocumentRepository.findByNameContains(name);
        ArrayList<Long> ids = new ArrayList<Long>();
        for (ContactDocument cd: contactDocuments) {
            ids.add(Long.valueOf(cd.getId()));
        }
        return contactRepository.findByIdIsIn(ids, new PageRequest(page, size));
    }

    public Contact findOne(Long id) {
	return contactRepository.findOne(id);
    }

    @Transactional
    public Contact save(Contact contact) {
	return contactRepository.save(contact);
    }

    public Page<Contact> findByCompany(Company company, PageRequest pageRequest) {
	return contactRepository.findByCompany(company, pageRequest);
    }

    public int deleteIdNotIn(List<Long> list) {
        return contactRepository.deleteIdNotIn(list);
    }

    public List<Contact> findByBirthDate(String date) {
        List<Long> ids = contactDocumentRepository.findByBirthDate(date);
        return contactRepository.findByIdIn(ids);
    }
    @Transactional
    public void delete(Long id) {
	contactRepository.delete(id);
    }
}
