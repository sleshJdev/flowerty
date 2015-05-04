package by.itechart.flowerty.persistence.repository;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.config.aware.JpaConfigurationAware;
import by.itechart.flowerty.persistence.model.Phone;

/**
 * @author Мария 31.03.15
 */
//@Ignore
public class TestPhoneRepository extends JpaConfigurationAware {
    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    public void delete(){
	phoneRepository.delete(2L);
    }
    
    @Test
    @Ignore
    public void savePhone_ValidPhones_ReturnsPageOfPhones() {
	Phone phone = new Phone();
	phone.setNumber("1232121");
	phone.setCountry("375");
	phone.setComment("PhoneComment");
	phoneRepository.save(phone);
    }

    @Test
    public void findPhone_ValidId_ReturnsPhone() {
	Phone phone = phoneRepository.findOne(1l);
	Assert.assertEquals(phone.getNumber(), "1232121");
    }

    @Test
    public void findPhone_InvalidId_ReturnsNull() {
	Phone phone = phoneRepository.findOne(1000l);
	Assert.assertNull(phone);
    }
}
