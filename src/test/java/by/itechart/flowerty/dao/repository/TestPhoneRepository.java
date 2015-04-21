package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.config.JpaConfigurationAware;
import by.itechart.flowerty.model.Phone;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 @author Мария 31.03.15
 */
public class TestPhoneRepository extends JpaConfigurationAware {
    @Autowired
    private PhoneRepository phoneRepository;
    @Test
    public void savePhone_ValidPhones_ReturnsPageOfPhones() {
        Phone phone = new Phone();
        phone.setNumber("1232121");
        phone.setCountry("375");
        //phone.setContact(contact);
        phone.setComment("PhoneComment");
        phoneRepository.save(phone);
    }
    @Ignore
    @Test
    public void findPhone_ValidId_ReturnsPhone() {
        Phone phone = phoneRepository.findOne(1l);
        Assert.assertEquals(phone.getNumber(), "1232121");
    }
    @Ignore
    @Test
    public void findPhone_InvalidId_ReturnsNull() {
        Phone phone = phoneRepository.findOne(1000l);
        Assert.assertNull(phone);
    }
//    @Test
//    public void findPhones_ValidContact_ReturnsPageOfPhones() {
//        Contact contact = new Contact();
//        contact.setId(1l);
//       // Page<Phone> phones = phoneRepository.findByContact(contact, new PageRequest(0, 10));
//        Assert.assertEquals(phones.getContent().get(0).getNumber(), "1232121");
//    }
//    @Test
//    public void findPhones_InvalidContact_ReturnsEmptyPage() {
//        Contact contact = null;
//        Page<Phone> phones = phoneRepository.findByContact(contact, new PageRequest(0, 10));
//        Assert.assertEquals(phones.getContent().size(), 0);
//    }
}
