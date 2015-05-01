package by.itechart.flowerty.persistence.repository;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import by.itechart.flowerty.persistence.model.Phone;


/**
 * @author Eugene Putsykovich(slesh) May 1, 2015
 * 
 *         custom phone repository to delete phones
 */
@NoRepositoryBean
public interface PhoneRepositoryCustom {
    public void deleteIdNotIn(Long contactId, List<Long> list);
}
