package test.by.itechart.flowerty.persistence.repository;

import org.joda.time.DateTime;

import by.itechart.flowerty.persistence.model.Contact;

/**
 * @author Eugene Putsykovich(slesh) May 4, 2015
 *
 *         provides data for check tests
 */
public abstract class TestRepositoryHelper {
    public static final Contact getContactWithIdOne() {
	Contact contact = new Contact();
	contact.setName("Anton");
	contact.setSurname("Antonov");
	contact.setFathername("Antonovich");
	contact.setBirthday(DateTime.parse("1990-12-03").toDate());
	contact.setEmail("anton@mail.com");

	return contact;
    }
}
