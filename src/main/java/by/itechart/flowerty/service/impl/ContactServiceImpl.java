package by.itechart.flowerty.service.impl;

import by.itechart.flowerty.dao.ContactDao;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 21.03.15
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactDao dao;

    @Override
    @Transactional
    public void saveContact(Contact contact) {
        dao.saveContact(contact);
    }

    @Override
    public Contact getContact(Integer id) {
        return dao.getContact(id);
    }

    @Override
    @Transactional
    public void deleteContactById(Integer id) {
        dao.deleteContactById(id);
    }

    @Override
    public List<Contact> getAllContacts() {
        return dao.getAllContacts();
    }

    @Override
    @Transactional
    public void updateContact(Contact contact) {
        dao.updateContact(contact);
    }
}
