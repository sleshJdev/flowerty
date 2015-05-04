package by.itechart.flowerty.persistence.repository;


import by.itechart.flowerty.config.aware.JpaConfigurationAware;
import by.itechart.flowerty.persistence.model.Phone;
import by.itechart.flowerty.persistence.model.QPhone;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Мария 31.03.15
 */
@Ignore
public class TestPhoneRepository extends JpaConfigurationAware {
    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    public void delete(){
	phoneRepository.delete(2L);
    }
    
    @Test
    public void test(){
	QPhone PHONE = QPhone.phone;
	
	List<Long> ids = new ArrayList<Long>();
	ids.add(1L);
	ids.add(2L);
	ids.add(3L);
	ids.add(4L);
	
	delete(PHONE).where(PHONE.id.eq(1L)).execute();
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