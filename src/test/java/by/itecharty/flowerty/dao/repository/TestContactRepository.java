package by.itecharty.flowerty.dao.repository;

import by.itechart.flowerty.dao.repository.ContactRepository;
import by.itechart.flowerty.model.Contact;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 26.03.15
 * Time: 7:22
 * To change this template use File | Settings | File Templates.
 */
@Ignore
public class TestContactRepository extends Assert {
    @Autowired
    ContactRepository contactRepository;
    @Test
    public void findContact() {
      //  contactRepository = RepositoryFactorySupport.
           Contact contact = contactRepository.findOne(1L);
            assertNotNull(contact);
    }
}
