package by.itechart.flowerty.dao.repository;

import by.itechart.flowerty.model.Contact;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author Maria
 *         Date: 17.04.15
 */
@NoRepositoryBean
public interface ContactRepositoryCustom {
    public List<Contact> findIdNotIn(List<Long> list);
}
