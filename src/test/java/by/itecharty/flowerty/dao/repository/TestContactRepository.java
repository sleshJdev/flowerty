package by.itecharty.flowerty.dao.repository;
import by.itechart.flowerty.dao.repository.ContactRepository;
import by.itechart.flowerty.model.*;
import by.itecharty.flowerty.config.JpaConfigurationAware;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: Мария
* Date: 26.03.15
* Time: 20:41
* To change this template use File | Settings | File Templates.
*/

public class TestContactRepository extends JpaConfigurationAware {
    @Autowired
    private ContactRepository contactRepository;

    @Test
    public void testFindByGoodId() {
        Contact contact = contactRepository.findOne(1l);
        Assert.assertNotNull(contact);
        Assert.assertEquals("TestName", contact.getName());
        Assert.assertEquals("TestSurname", contact.getSurname());
        Assert.assertEquals(Long.valueOf(1), contact.getAddress().getId());
        Assert.assertEquals(Long.valueOf(1), contact.getCompany().getId());
        Assert.assertEquals("f&j", contact.getCompany().getName());
    }
    @Test
    public void testFindByBadId() {
        Contact contact = contactRepository.findOne(1000l);
        Assert.assertNull(contact);
    }
    @Test
    public void testGetAllContactsOfCompanyGoodId() {
        Company company = new Company();
        company.setId(1l);
        List<Contact> contactList = contactRepository.findByCompany(company);
        Assert.assertNotNull(contactList);
        Assert.assertTrue(contactList.size()>0);
    }

    @Test
    public void testGetAllContactsOfCompanyBadId() {
        Company company = new Company();
        company.setId(1000l);
        List<Contact> contactList = contactRepository.findByCompany(company);
        Assert.assertNotNull(contactList);
        Assert.assertEquals(contactList.size(), 0);
    }

    @Test
    public void testSaveContactGoodData() {
        Contact contact = new Contact();
        Company company = new Company();
        company.setId(1l);
        contact.setCompany(company);
        contact.setName("Ivan");
        contact.setSurname("Ivanov");
        contact.setEmail("123@qwe.ru");
        Address address = new Address();
        address.setId(1l);
        contact.setAddress(address);
        Contact contact1 = contactRepository.save(contact);
        Assert.assertNotNull(contact1);
        Assert.assertNotNull(contact1.getId());
        Assert.assertEquals(address, contact1.getAddress());
        Assert.assertEquals(company, contact1.getCompany());
        Assert.assertEquals("Ivan", contact1.getName());
        Assert.assertEquals("Ivanov", contact1.getSurname());
        Assert.assertEquals("123@qwe.ru", contact1.getEmail());

    }
    @Test
    public void testSaveContactBadData() {
        Contact contact = new Contact();
        Company company = new Company();
        company.setId(1000l);
        contact.setCompany(company);
        contact.setName("Ivan");
        contact.setSurname("Ivanov");
        contact.setEmail("123@qwe.ru");
        Address address = new Address();
        address.setId(1l);
        contact.setAddress(address);
        try {
        Contact contact1 = contactRepository.save(contact);
        } catch (Exception ex) {
            return;
        }
        Assert.assertEquals(1, 2);
    }
    @Test
    public void testGetPageGood() {
        Page<Contact> contacts = contactRepository.findAll(new PageRequest(1, 5));
        Assert.assertEquals(5, contacts.getSize());
    }
    @Test
    public void testGetPageBad() {
        Page<Contact> contacts = contactRepository.findAll(new PageRequest(5, 20));
        Assert.assertEquals(0, contacts.getContent().size());
    }
    @Test
    public void deleteGoodId() {
        contactRepository.delete(2l);

    }
}
