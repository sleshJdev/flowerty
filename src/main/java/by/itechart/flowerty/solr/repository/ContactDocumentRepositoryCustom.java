package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.persistence.model.Address;
import by.itechart.flowerty.solr.model.ContactDocument;

import java.util.List;

/**
 * @author Maria Date: 16.04.15
 */
public interface ContactDocumentRepositoryCustom {
    public List<Long> findByBirthDate(String birthday);

    public List<Long> findBySearch(ContactDocument contactDocument);

    public List<Long> findBySurnameStartsWithAndCompany(String surname, Long company);

    public Long findByAddress(Address address);
}
