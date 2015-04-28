package by.itechart.flowerty.dao;
import by.itechart.flowerty.model.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactDao {
    public void saveContact (Contact contact);
    public Contact getContact(Integer id);
    public void deleteContactById (Integer id);
    public List<Contact> getAllContacts();
    public void updateContact(Contact contact);
}
