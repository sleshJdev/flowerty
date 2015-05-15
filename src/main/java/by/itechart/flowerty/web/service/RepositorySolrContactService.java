package by.itechart.flowerty.web.service;

import by.itechart.flowerty.solr.model.ContactDocument;
import by.itechart.flowerty.solr.repository.ContactDocumentRepository;
import by.itechart.flowerty.persistence.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

/**
* @author Maria
*         Date: 11.04.15
*/
@Service
public class RepositorySolrContactService {//implements SolrContactService {
 //  @Resource
    //@Autowired
    //private RepositoryContactIndexService indexService;

//    @Resource
    @Autowired
    private ContactDocumentRepository repository;

    @PreAuthorize("hasPermission('Contact', 'add')")
    @Transactional
    public ContactDocument add(Contact added) {
        ContactDocument model = added.getContactDocument(); //ContactDocument.getBuilder(added.getId(), added.getName())
        ContactDocument persisted = repository.save(model);
        return persisted;
    }

      public List<ContactDocument> findByNameContains(String name) {
          return repository.findByNameContains(name);
      }
    public List<Long> findByBirthDate (String birthday) {
         return repository.findByBirthDate(birthday);
    }
    public List<Long> findBySurnameStartsWithAndCompany(String surname, Long company) {
        return repository.findBySurnameStartsWithAndCompany(surname, company);
    }
}
