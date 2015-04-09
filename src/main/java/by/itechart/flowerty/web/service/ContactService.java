package by.itechart.flowerty.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.itechart.flowerty.dao.repository.ContactRepository;
import by.itechart.flowerty.model.Company;
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

    @Transactional
    public void delete(Long id) {
	contactRepository.delete(id);
    }
}
