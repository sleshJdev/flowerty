package by.itechart.flowerty.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.config.aware.JpaConfigurationAware;
import by.itechart.flowerty.persistence.repository.model.Phone;

/**
 * @author Мария 31.03.15
 */
//@Ignore
public class TestPhoneRepository extends JpaConfigurationAware {
    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    public void delete(){
	phoneRepository.delete(1L);
    }
    
    @Test
    public void deleteByIdNotIn_ValidArrayOfId_RemoveTheyPhones(){
	List<Long> ids = new ArrayList<Long>();
	ids.add(3L);
	ids.add(4L);
	ids.add(5L);
	ids.add(6L);
	
	phoneRepository.deleteIdNotIn(ids);
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
