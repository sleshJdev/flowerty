package by.itechart.flowerty.solr.repository;

import by.itechart.flowerty.solr.model.ContactDocument;

import java.util.List;

/**
 * @author Maria Date: 16.04.15
 */
public interface ContactDocumentRepositoryCustom {
    public void deleteIdIsIn(List<String> ids);
    
    public List<Long> findByBirthDate(String birthday);

    public List<Long> findBySearch(ContactDocument contactDocument);

    public List<Long> findBySurnameStartsWithAndCompany(String surname, Long company);
<<<<<<< HEAD
=======

    public Long findByAddress(Address address);
>>>>>>> c9942b2bd55f4269aef40ea923801c6a64157af9
}
