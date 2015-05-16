package by.itechart.flowerty.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import by.itechart.flowerty.persistence.model.Contact;
import by.itechart.flowerty.persistence.model.QContact;

/**
 * @author Maria Date: 17.04.15
 */
public class ContactRepositoryImpl extends QueryDslRepositorySupport implements ContactRepositoryCustom {
    private static final QContact CONTACT = QContact.contact;
    
    public ContactRepositoryImpl(){
	super(Contact.class);
    }
    
    @Override
    @Transactional
    public int deleteIdIsIn(List<Long> list) {
	return (int) delete(CONTACT).where(CONTACT.id.in(list)).execute();
    }
}
