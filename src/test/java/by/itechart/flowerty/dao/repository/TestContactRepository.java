package by.itechart.flowerty.dao.repository;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import by.itechart.flowerty.config.JpaConfigurationAware;
import by.itechart.flowerty.model.Address;
import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Contact;
import by.itechart.flowerty.web.service.ContactService;

/**
 *@author Мария Date: 26.03.15
 */
public class TestContactRepository extends JpaConfigurationAware {
	@Autowired
	private ContactService contactRepository;

	@Test
	public void findContact_ValidId_ContactReturned() {
		Contact contact = contactRepository.findOne(2l);
		Assert.assertNotNull(contact);
		Assert.assertEquals(contact.getName(), "Sergey");
		Assert.assertEquals(contact.getSurname(), "Sergeev");
		Assert.assertNotNull(contact.getAddress());
		Assert.assertEquals(contact.getAddress().getCountry(), "Belarus");
	}

    @Ignore
	@Test
	public void findContact_InvalidId_NullReturned() {
		Contact contact = contactRepository.findOne(1000l);
		Assert.assertNull(contact);
	}

    @Ignore
	@Test
	public void findContactByCompany_ValidCompany_PageOfContactsReturned() {
		Company company = new Company();
		company.setId(1l);
		Page<Contact> page = contactRepository.findByCompany(company, new PageRequest(0, 10));
		Assert.assertNotNull(page);
		Assert.assertNotEquals(0, page.getContent().size());
	}

    @Ignore
	@Test
	public void findContact_InvalidCompany_EmptyPageReturned() {
		Company company = new Company();
		company.setId(1000l);
		Page<Contact> page = contactRepository.findByCompany(company, new PageRequest(1, 10));
		Assert.assertNotNull(page);
		Assert.assertEquals(0, page.getNumberOfElements());
	}

	@Ignore
	@Test
	public void saveContact_CorrectContact_SameContactReturned() {
		Company company = new Company();
		company.setId(2l);
		Address address = new Address();
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

	@Ignore
	@Test
	public void deleteContactValidId() {
		contactRepository.delete(6l);
	}

	@Ignore
	@Test
	public void deleteContactInvalidId() {
		contactRepository.delete(3l);
	}
}
