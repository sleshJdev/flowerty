package by.itecharty.flowerty.dao.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import by.itechart.flowerty.dao.repository.ContactRepository;
import by.itechart.flowerty.model.Address;
import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Contact;
import by.itecharty.flowerty.config.JpaConfigurationAware;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 26.03.15
 * Time: 7:22
 * To change this template use File | Settings | File Templates.
 */
public class TestContactRepository extends JpaConfigurationAware {
	@Autowired
	private ContactRepository contactRepository;

	@Test
	public void findContactGoodId() {
		Contact contact = contactRepository.findOne(1l);
		Assert.assertNotNull(contact);
		Assert.assertEquals(contact.getName(), "TestName");
		Assert.assertEquals(contact.getSurname(), "TestSurname");
		Assert.assertNotNull(contact.getAddress());
		Assert.assertEquals(contact.getAddress().getCountry(), "Belarus");
	}

	@Test
	public void findContactBadId() {
		Contact contact = contactRepository.findOne(1000l);
		Assert.assertNull(contact);
	}

	@Test
	public void findByGoodCompany() {
		Company company = new Company();
		company.setId(1l);
		Page page = contactRepository.findByCompany(company, new PageRequest(0, 10));
		Assert.assertNotNull(page);
		Assert.assertNotEquals(0, page.getContent().size());
	}

	@Test
	public void findByBadCompany() {
		Company company = new Company();
		company.setId(1000l);
		Page page = contactRepository.findByCompany(company, new PageRequest(1, 10));
		Assert.assertNotNull(page);
		Assert.assertEquals(0, page.getNumberOfElements());
	}

	@Test
	public void saveContact() {
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
    public void deleteContactValidId() {
        contactRepository.delete(6l);
    }
//    @Test
//    public void deleteContactInvalidId() {
//        try {
//        contactRepository.delete(3l);
//        } catch (Exception ex) {
//
//        }
//    }
//    @Test
//    public void getShortContactsGoodCompany() {
//        Company company = new Company();
//        company.setId(1l);
//        List<Contact> list = contactRepository.getShortContacts(company);
//        Assert.assertNotNull(list);
//        Assert.assertNotEquals(list.size(), 0);
//        Assert.assertNotNull(list.get(0));
//        Assert.assertEquals("Petr", list.get(0).getName());
//        Assert.assertNull(list.get(0).getEmail());
//    }
//    @Test
//    public void getShortContactsBadCompany() {
//        Company company = new Company();
//        company.setId(5l);
//        List<Contact> list = contactRepository.getShortContacts(company);
//        Assert.assertNotNull(list);
//        Assert.assertEquals(list.size(), 0);
//    }
    }
