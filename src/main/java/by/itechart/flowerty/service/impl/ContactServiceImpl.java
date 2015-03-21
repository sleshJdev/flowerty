package by.itechart.flowerty.service.impl;

import by.itechart.flowerty.dao.ContactDao;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 21.03.15
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactDao dao;

    @Override
    public void saveContact(Contact contact) {
        dao.saveContact(contact);
    }

    @Override
    public Contact getContact(Integer id) {
        return dao.getContact(id);
    }

    @Override
    public void deleteContactById(Integer id) {
        dao.deleteContactById(id);
    }

    @Override
    public List<Contact> getAllContacts() {
        return dao.getAllContacts();
    }

    @Override
    public void updateContact(Contact contact) {
        dao.updateContact(contact);
    }
}
