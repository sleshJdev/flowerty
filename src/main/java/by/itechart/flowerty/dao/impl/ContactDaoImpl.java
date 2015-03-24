package by.itechart.flowerty.dao.impl;

import by.itechart.flowerty.dao.AbstractDao;
import by.itechart.flowerty.dao.ContactDao;
import by.itechart.flowerty.model.Contact;

import org.hibernate.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("contactDao")
public class ContactDaoImpl extends AbstractDao implements ContactDao {

    @Override
    public void saveContact(Contact contact) {
        persist(contact);
    }
    
    @Override
    public Contact getContact(Integer id) {
        return (Contact) getSession().load(Contact.class, id);
    }

    @Override
    public void deleteContactById(Integer id) {
        Query query = getSession().createSQLQuery("delete from Contact where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Contact> getAllContacts() {
        Criteria criteria = getSession().createCriteria(Contact.class);
        return (List<Contact>) criteria.list();
    }
    @Override
    public void updateContact(Contact contact) {
        getSession().update(contact);
    }
}
