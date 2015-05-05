package test.by.itechart.flowerty.persistence.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.persistence.repository.ContactRepository;
import test.by.itechart.flowerty.config.aware.JpaConfigurationAware;

/**
 * @author Eugene Putsykovich(slesh) May 4, 2015
 *
 */
public class ContactRepositoryTests extends JpaConfigurationAware{
    @Autowired
    private ContactRepository contactRepository;
    
    @Test
    public void findOne_PassValidId_ReturnsNotNullContact(){
	final Contact expectedContact = TestRepositoryHelper.getContactWithIdOne();
	
	final Contact actualContact = contactRepository.findOne(1L);
    
	Assert.assertEquals(expectedContact.getName(), actualContact.getName());
    }
}
