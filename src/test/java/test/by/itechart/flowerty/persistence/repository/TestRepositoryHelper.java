package test.by.itechart.flowerty.persistence.repository;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

import by.itechart.flowerty.persistence.model.Address;
import by.itechart.flowerty.persistence.model.Company;
import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.persistence.model.Phone;
import by.itechart.flowerty.persistence.model.Phone.PHONE_TYPE;

/**
 * @author Eugene Putsykovich(slesh) May 4, 2015
 *
 *         provides data for check tests
 */
public abstract class TestRepositoryHelper {
    public static final Contact getContactWithIdOne() {
	Address address = new Address();
	address.setId(1L);
	address.setTown("Minsk");
	address.setStreet("Kirova");
	address.setFlat("11");
	address.setHouse("10");
	address.setCountry("Belarus");
	
	Company company = new Company();
	company.setId(1L);
	company.setName("FandJ");
	company.setWebsite("www.FandJ.com");
	
	Phone phone1 = new Phone();
	phone1.setId(1L);
	phone1.setCountry("12");
	phone1.setOperator("34");
	phone1.setNumber("56789");
	phone1.setType(PHONE_TYPE.CELL);
	phone1.setComment("Comment1");
	
	Phone phone2 = new Phone();
	phone2.setId(2L);
	phone2.setCountry("67");
	phone2.setOperator("89");
	phone2.setNumber("01234");
	phone2.setType(PHONE_TYPE.HOME);
	phone2.setComment("Comment2");
	
	Set<Phone> phones = new HashSet<Phone>();
	phones.add(phone1);
	phones.add(phone2);
	
	Contact contact = new Contact();
	contact.setId(1L);
	contact.setName("Anton");
	contact.setSurname("Antonov");
	contact.setFathername("Antonovich");
	contact.setBirthday(DateTime.parse("1990-12-03").toDate());
	contact.setEmail("anton@mail.com");
	contact.setAddress(address);
	contact.setPhones(phones);
	contact.setCompany(company);
	
	return contact;
    }
}
