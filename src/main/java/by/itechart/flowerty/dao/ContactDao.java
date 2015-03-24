package by.itechart.flowerty.dao;
import by.itechart.flowerty.model.Contact;

import java.util.List;

public interface ContactDao {
    void saveContact (Contact contact);
    Contact getContact(Integer id);
    void deleteContactById (Integer id);
    List<Contact> getAllContacts();
    void updateContact(Contact contact);
}
