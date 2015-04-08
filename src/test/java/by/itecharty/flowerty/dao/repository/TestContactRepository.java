package by.itecharty.flowerty.dao.repository;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import by.itechart.flowerty.dao.repository.ContactRepository;
import by.itechart.flowerty.model.Address;
import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Contact;
import by.itecharty.flowerty.config.JpaConfigurationAware;

/**
 *@author Мария Date: 26.03.15
 */
@Ignore
public class TestContactRepository extends JpaConfigurationAware {
    private static Validator validator;
    @Autowired
    private ContactRepository contactRepository;
    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void findContact_ValidId_ContactReturned() {
           Contact contact = contactRepository.findOne(1l);
            Assert.assertNotNull(contact);
        Assert.assertEquals(contact.getName(), "Anton");
        Assert.assertEquals(contact.getSurname(), "Antonov");
        Assert.assertNotNull(contact.getAddress());
        Assert.assertEquals(contact.getAddress().getCountry(), "Belarus");
    }
    @Test
    public void findContact_InvalidId_NullReturned() {
        Contact contact = contactRepository.findOne(1000l);
        Assert.assertNull(contact);
    }
    @Test
    public void findContactByCompany_ValidCompany_PageOfContactsReturned() {
        Company company = new Company();
        company.setId(1l);
        Page page = contactRepository.findByCompany(company, new PageRequest(0, 10));
        Assert.assertNotNull(page);
        Assert.assertNotEquals(0, page.getContent().size());
    }
    @Test
    public void findContact_InvalidCompany_EmptyPageReturned() {
        Company company = new Company();
        company.setId(1000l);
        Page page = contactRepository.findByCompany(company, new PageRequest(1, 10));
        Assert.assertNotNull(page);
        Assert.assertEquals(0, page.getNumberOfElements());
    }
    @Test
    public void saveContact_CorrectContact_SameContactReturned() {
        Company company = new Company();
        company.setId(2l);
        Address address = new Address();
        address.setId(1l);
        Contact contact = new Contact();
        contact.setCompany(company);
        contact.setAddress(address);
        contact.setName("Petr");
        contact.setSurname("Petrov");
        contact.setEmail("petrov@mail.com");
        contact = contactRepository.save(contact);
        Assert.assertNotNull(contact);
        Assert.assertNotNull(contact.getAddress());
        Assert.assertNotNull(contact.getCompany());
        Assert.assertEquals("Petr", contact.getName());
    }
    @Test
    public void saveContact_InvalidContact_ThrowsException() {
        Company company = new Company();
        company.setId(2l);
        Address address = new Address();
        address.setId(1l);
        Contact contact = new Contact();
        contact.setCompany(company);
        contact.setAddress(address);
        contact.setName("Petr");
        contact.setSurname("Petrov");
        contact.setEmail("petrov!!@mail.com");
        try {
        contact = contactRepository.save(contact);
        } catch (ExceptionInInitializerError ex) {
            return;
        }
        Assert.assertEquals(1,2);
    }
//    @Test
//    public void deleteContactValidId() {
//        contactRepository.delete(6l);
//    }


	@Ignore
	@Test
	public void deleteContactInvalidId() {
		contactRepository.delete(3l);
	}
}
