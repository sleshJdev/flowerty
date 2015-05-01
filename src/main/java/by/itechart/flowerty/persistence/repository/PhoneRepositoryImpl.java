package by.itechart.flowerty.persistence.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import by.itechart.flowerty.persistence.repository.model.Phone;
import by.itechart.flowerty.persistence.repository.model.QPhone;

/**
 * @author Eugene Putsykovich(slesh) May 1, 2015
 * 
 *         delete phones by ids
 */
public class PhoneRepositoryImpl extends QueryDslRepositorySupport implements PhoneRepositoryCustom {
    private  QPhone PHONE = QPhone.phone;
    
    public PhoneRepositoryImpl() {
	super(Phone.class);
    }

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public int deleteIdNotIn(List<Long> list) {
	delete(PHONE).where(PHONE.id.notIn(list));

	return 0;
    }
}
