package by.itechart.flowerty.persistence.repository;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;


/**
 * @author Eugene Putsykovich(slesh) May 1, 2015
 * 
 *         custom phone repository to delete phones
 */
@NoRepositoryBean
public interface PhoneRepositoryCustom {
    public int deleteIdNotIn(List<Long> list);
}
