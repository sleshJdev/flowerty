package by.itechart.flowerty.persistence.repository;

import by.itechart.flowerty.persistence.model.Phone;
import by.itechart.flowerty.persistence.model.QPhone;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;


/**
 * @author Eugene Putsykovich(slesh) May 1, 2015
 * 
 *         delete phones by id
 */
public class PhoneRepositoryImpl extends QueryDslRepositorySupport implements PhoneRepositoryCustom {
    private static final QPhone PHONE = QPhone.phone;

    public PhoneRepositoryImpl() {
	super(Phone.class);
    }

    @Override
    @javax.transaction.Transactional
    public int deleteIdNotIn(Long contactId, List<Long> list) {
	return (int) delete(PHONE).where(PHONE.id.notIn(list).and(PHONE.contact.id.eq(contactId))).execute();
    }
}
