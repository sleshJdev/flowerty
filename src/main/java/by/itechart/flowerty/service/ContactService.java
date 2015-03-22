package by.itechart.flowerty.service;

import by.itechart.flowerty.model.Contact;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 21.03.15
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
public interface ContactService {
    void saveContact (Contact contact);
    Contact getContact(Integer id);
    void deleteContactById (Integer id);
    List<Contact> getAllContacts();
    void updateContact(Contact contact);
}
