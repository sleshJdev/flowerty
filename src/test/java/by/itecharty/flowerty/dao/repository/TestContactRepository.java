package by.itecharty.flowerty.dao.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.dao.repository.ContactRepository;
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
	ContactRepository contactRepository;
	
	@Test
	public void findContact() {
		Contact contact = contactRepository.findOne(1L);
		Assert.assertNotNull(contact);
	}
}
