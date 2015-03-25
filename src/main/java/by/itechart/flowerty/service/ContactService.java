package by.itechart.flowerty.service;

import by.itechart.flowerty.model.Contact;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 21.03.15
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
@Component
public interface ContactService {
    public void saveContact (Contact contact);
    public Contact getContact(Integer id);
    public void deleteContactById (Integer id);
    public List<Contact> getAllContacts();
    public void updateContact(Contact contact);
}
