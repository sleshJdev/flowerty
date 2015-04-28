package by.itechart.flowerty.web.service;

import by.itechart.flowerty.solr.model.ContactDocument;
import by.itechart.flowerty.solr.repository.ContactDocumentRepository;
import by.itechart.flowerty.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import javax.annotation.Resource;
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

 /*  @PreAuthorize("hasPermission('Contact', 'delete')")
    @Transactional
    public Contact deleteById(Long id) {
        Contact deleted = findById(id);
        repository.delete(deleted);
        return deleted;
    }     */
      public List<ContactDocument> findByNameContains(String name) {
          return repository.findByNameContains(name);
      }
    public List<Long> findByBirthDate (String birthday) {
         return repository.findByBirthDate(birthday);
    }

   /* @PreAuthorize("hasPermission('Contact', 'update')")
    @Transactional
    @Override
    public Contact update(Contact updated) {
        //Todo model = findById(updated.getId());
      //  repository.save(updated.buildContactDocument());
        //model.update(updated.getDescription(), updated.getTitle());
     //   Contact contact = new Contact(); //createByUpdated
     //   indexService.addToIndex(updated);

        return updated;
    }    */
  //  @PreAuthorize("hasPermission('Contact', 'search')")
    //@Override
   // public List<ContactDocument> search(String searchTerm) {
     //   return indexService.search}
}
