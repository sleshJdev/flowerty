package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Eugene Putsykovich(slesh) May 1, 2015
 * 
 *         delete phones by id
 */
public class PhoneRepositoryImpl extends QueryDslRepositorySupport implements PhoneRepositoryCustom {
 //   private  static final QPhone PHONE = QPhone.phone;
    
    public PhoneRepositoryImpl() {
	super(Phone.class);
    }

    @Autowired
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public void deleteIdNotIn(Long contactId, List<Long> list) {
//	delete(PHONE).where(PHONE.id.notIn(list).and(PHONE.contact.id.eq(contactId))).execute();
    }
}
