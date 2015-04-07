package by.itechart.flowerty.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.dao.repository.ContactRepository;
import by.itechart.flowerty.model.Contact;

/**
 * @author Eugene Putsykovich(slesh) Apr 5, 2015
 *
 */
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Page<Contact> getPage(int page, int size) {
	return contactRepository.findAll(new PageRequest(page, size));
    }

    public Contact getById(Long id) {
	return contactRepository.findOne(id);
    }

    public void save(Contact contact) {
	contactRepository.save(contact);
    }
}
